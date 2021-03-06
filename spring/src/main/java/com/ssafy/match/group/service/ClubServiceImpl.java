package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.clubboard.board.entity.ClubBoard;
import com.ssafy.match.group.clubboard.board.repository.ClubBoardRepository;
import com.ssafy.match.group.dto.MemberDto;
import com.ssafy.match.group.dto.club.ClubDto;
import com.ssafy.match.group.dto.club.request.ClubApplicationRequestDto;
import com.ssafy.match.group.dto.club.request.ClubCreateRequestDto;
import com.ssafy.match.group.dto.club.request.ClubUpdateRequestDto;
import com.ssafy.match.group.dto.club.response.ClubFormInfoResponseDto;
import com.ssafy.match.group.dto.club.response.ClubInfoResponseDto;
import com.ssafy.match.group.dto.club.response.InfoForApplyClubFormResponseDto;
import com.ssafy.match.group.dto.study.response.StudyInfoResponseDto;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.club.ClubApplicationForm;
import com.ssafy.match.group.entity.club.CompositeMemberClub;
import com.ssafy.match.group.entity.club.MemberClub;
import com.ssafy.match.group.entity.study.Study;
import com.ssafy.match.group.repository.club.ClubApplicationFormRepository;
import com.ssafy.match.group.repository.club.ClubRepository;
import com.ssafy.match.group.repository.club.MemberClubRepository;
import com.ssafy.match.group.studyboard.board.entity.StudyBoard;
import com.ssafy.match.member.entity.Member;
import com.ssafy.match.member.entity.MemberSns;
import com.ssafy.match.member.repository.MemberBeginnerTechstackRepository;
import com.ssafy.match.member.repository.MemberExperiencedTechstackRepository;
import com.ssafy.match.member.repository.MemberRepository;
import com.ssafy.match.member.repository.MemberSnsRepository;
import com.ssafy.match.util.SecurityUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
// https://velog.io/@kdhyo/JavaTransactional-Annotation-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90-26her30h
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final MemberClubRepository memberClubRepository;
    private final ClubApplicationFormRepository clubApplicationFormRepository;
    private final DBFileRepository dbFileRepository;
    private final MemberExperiencedTechstackRepository memberExperiencedTechstackRepository;
    private final MemberBeginnerTechstackRepository memberBeginnerTechstackRepository;
    private final MemberSnsRepository memberSnsRepository;
    private final ClubBoardRepository clubBoardRepository;

    @Transactional
    public Long create(ClubCreateRequestDto dto) throws Exception {
        validCity(dto.getCity());

        Club club = new Club(dto);
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        club.changeMember(member);
        club.setDbFile(findDBFile(dto.getUuid()));
        clubRepository.save(club);

        makeBasicBoards(club);
        addMember(club, member);

        return club.getId();
    }

    @Transactional
    public HttpStatus update(Long clubId, ClubUpdateRequestDto dto) throws Exception {
        validCity(dto.getCity());

        Club club = findClub(clubId);
        Long memberId = SecurityUtil.getCurrentMemberId();
        if (!club.getMember().getId().equals(memberId)) {
            throw new Exception("????????? ????????????.");
        }

        club.update(dto);
        club.changeMember(findMember(dto.getHostId()));
        club.setDbFile(findDBFile(dto.getUuid()));

        return HttpStatus.OK;
    }

    @Transactional
    public HttpStatus delete(Long clubId) throws Exception {
        Club club = findClub(clubId);

        if (!club.getMember().getId().equals(SecurityUtil.getCurrentMemberId())) {
            throw new Exception("????????? ????????????.");
        }

        List<MemberClub> memberClubs = memberClubRepository.findMemberRelationInClub(club);
        for (MemberClub mem : memberClubs) {
            mem.deActivation();
        }

        club.setIsActive(false);

        return HttpStatus.OK;
    }

    public Page<ClubInfoResponseDto> getAllClub(Pageable pageable) {
        Page<ClubInfoResponseDto> clubInfoResponseDtos = clubRepository.findByIsActiveAndIsPublic(Boolean.TRUE, Boolean.TRUE, pageable)
                .map(ClubInfoResponseDto::of);

        for (ClubInfoResponseDto clubInfoResponseDto: clubInfoResponseDtos.getContent()) {
            clubInfoResponseDto.setMemberDtos(makeMemberDtos(memberClubRepository.findMemberByClubId(clubInfoResponseDto.getId())));
        }
        return clubInfoResponseDtos;
    }

    // ?????? ?????? ?????? ??????
    public ClubInfoResponseDto getOneClub(Long clubId) throws Exception {
        Club club = findClub(clubId);

        if (!SecurityUtil.getCurrentMemberId().equals(club.getMember().getId())
            && !club.getIsPublic()) {
            throw new Exception("???????????? ???????????????.");
        }
        ClubInfoResponseDto clubInfoResponseDto = clubRepository.findById(clubId).map(ClubInfoResponseDto::of).orElseThrow(() -> new NullPointerException("????????? ???????????? ????????????"));
        clubInfoResponseDto.setMemberDtos(makeMemberDtos(memberClubRepository.findMemberByClubId(clubInfoResponseDto.getId())));
        return clubInfoResponseDto;
    }

    @Transactional
    public void addMember(Club club, Member member) {
        CompositeMemberClub compositeMemberClub = new CompositeMemberClub(member, club);

        // DB??? ?????? ?????? ????????? ????????? ?????? ??????
        MemberClub memberClub = memberClubRepository
            .findById(compositeMemberClub)
            .orElseGet(() -> MemberClub.builder()
                .compositeMemberClub(compositeMemberClub)
                .build());

        memberClub.activation();
        club.addMember();

        memberClubRepository.save(memberClub);
    }

    @Transactional
    public HttpStatus removeMember(Long clubId) throws Exception {
        Club club = findClub(clubId);
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new NullPointerException("????????? ????????? ?????????.(????????? ??????)"));
        if (club.getMember().getId().equals(member.getId())) {
            throw new Exception("???????????? ????????? ??? ????????????.");
        }
        CompositeMemberClub compositeMemberClub = new CompositeMemberClub(member, club);
        MemberClub memberClub = memberClubRepository.findById(compositeMemberClub)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));
        memberClub.deActivation();
        club.removeMember();
        return HttpStatus.OK;
    }

    @Transactional
    public void makeBasicBoards(Club club){
        clubBoardRepository.save(new ClubBoard("????????????", club));
        clubBoardRepository.save(new ClubBoard("?????????", club));
    }

    // ?????? ?????? ?????? ??????
    public Club findClub(Long clubId) throws Exception {
        Club club = clubRepository.findById(clubId)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));

        if (!club.getIsActive()) {
            throw new Exception("????????? ???????????????.");
        }

        return club;
    }

    public Member findMember(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));

        if (!member.getIs_active()) {
            throw new Exception("????????? ???????????????.");
        }

        return member;
    }

    // ?????? ??? ??????????????? ??????
    public DBFile findDBFile(String uuid) {
        if (uuid == null) {
            return null;
        }
        return dbFileRepository.findById(uuid)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));
    }

    // ?????? ????????? ?????? ?????? ?????????
    public List<Member> findMemberInClub(Club club) {
        return memberClubRepository.findMemberInClub(club);
    }

    // ????????? id, name, nickname??? ????????? (org.springframework.core.convert.ConverterNotFoundException ??????)
    public List<MemberDto> makeMemberDtos(List<Member> members) {
        List<MemberDto> memberDtos = new ArrayList<>();

        for (Member member : members) {
            memberDtos.add(new MemberDto(member));
        }

        return memberDtos;
    }

    public void validCity(String city) throws Exception {
        if (!Stream.of(City.values()).map(Enum::name)
            .collect(Collectors.toList()).contains(city)) {
            throw new Exception("???????????? ?????? ???????????????");
        }
    }

    // ?????? ?????? ????????? ?????? ?????? ??? ?????? ??????
    public InfoForApplyClubFormResponseDto getInfoForApply(Long clubId) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Club club = findClub(clubId);

        List<Member> memberList = findMemberInClub(club);
        for (Member mem : memberList) {
            if (SecurityUtil.getCurrentMemberId().equals(mem.getId())) {
                throw new Exception("?????? ????????? ???????????????.");
            }
        }

        InfoForApplyClubFormResponseDto dto = InfoForApplyClubFormResponseDto.builder()
            .nickname(member.getNickname())
            .city(member.getCity())
            .experiencedTechstack(memberExperiencedTechstackRepository.findTechstackByMemberName(member))
            .beginnerTechstack(memberBeginnerTechstackRepository.findTechstackByMemberName(member))
            .build();

        Optional<MemberSns> git = memberSnsRepository.findByMemberAndSnsName(member, "github");
        Optional<MemberSns> twitter = memberSnsRepository.findByMemberAndSnsName(member, "twitter");
        Optional<MemberSns> facebook = memberSnsRepository
            .findByMemberAndSnsName(member, "facebook");
        Optional<MemberSns> backjoon = memberSnsRepository
            .findByMemberAndSnsName(member, "backjoon");

        if (git.isPresent()) {
            dto.setGit(git.get().getSnsAccount());
        }
        if (twitter.isPresent()) {
            dto.setTwitter(twitter.get().getSnsAccount());
        }
        if (facebook.isPresent()) {
            dto.setFacebook(facebook.get().getSnsAccount());
        }
        if (backjoon.isPresent()) {
            dto.setBackjoon(backjoon.get().getSnsAccount());
        }

        return dto;
    }

    @Transactional
    public HttpStatus applyClub(Long clubId, ClubApplicationRequestDto dto) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Club club = findClub(clubId);

        CompositeMemberClub cmp = new CompositeMemberClub(member, club);

        Optional<ClubApplicationForm> form = clubApplicationFormRepository.findById(cmp);
        if (form.isPresent()) {
            throw new Exception("????????? ????????? ???????????????.");
        }

        ClubApplicationForm clubApplicationForm = new ClubApplicationForm(cmp, dto);

        clubApplicationForm.setDbFile(findDBFile(dto.getUuid()));

        clubApplicationFormRepository.save(clubApplicationForm);
        return HttpStatus.OK;
    }

    // ?????? ????????? ????????? ?????? ???????????? ??????
    public List<ClubFormInfoResponseDto> getAllClubForm(Long clubId) throws Exception {
        Club club = findClub(clubId);

        if (!SecurityUtil.getCurrentMemberId().equals(club.getMember().getId())) {
            throw new Exception("?????? ????????? ????????????.");
        }

        List<ClubApplicationForm> forms = clubApplicationFormRepository.formByClubId(club);
        List<ClubFormInfoResponseDto> clubFormInfoResponseDtos = new ArrayList<>();

        for (ClubApplicationForm form : forms) {
            clubFormInfoResponseDtos.add(ClubFormInfoResponseDto.builder()
                .form(form)
                .experiencedTechstack(memberExperiencedTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberClub().getMember()))
                .beginnerTechstack(memberBeginnerTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberClub().getMember()))
                .build());

        }

        return clubFormInfoResponseDtos;
    }

    // ??????????????? ????????? ??????
    public List<ClubFormInfoResponseDto> getAllFormByClubNickname(Long clubId, String nickname)
        throws Exception {
        Club club = findClub(clubId);

        if (!SecurityUtil.getCurrentMemberId().equals(club.getMember().getId())) {
            throw new Exception("?????? ????????? ????????????.");
        }

        List<ClubApplicationForm> forms = clubApplicationFormRepository
            .allFormByClubNickname(club, nickname);
        List<ClubFormInfoResponseDto> clubFormInfoResponseDtos = new ArrayList<>();

        for (ClubApplicationForm form : forms) {
            clubFormInfoResponseDtos.add(ClubFormInfoResponseDto.builder()
                .form(form)
                .experiencedTechstack(memberExperiencedTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberClub().getMember()))
                .beginnerTechstack(memberBeginnerTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberClub().getMember()))
                .build());
        }

        return clubFormInfoResponseDtos;
    }

    // ????????? ????????? ?????? ???????????? ????????? ?????? ????????? ????????????
    public ClubFormInfoResponseDto getOneClubForm(Long clubId, Long memberId) throws Exception {
        CompositeMemberClub cms = new CompositeMemberClub(findMember(memberId),
            findClub(clubId));

        ClubApplicationForm form = clubApplicationFormRepository.oneFormById(cms)
            .orElseThrow(() -> new NullPointerException("???????????? ?????? ??????????????????"));

        return ClubFormInfoResponseDto.builder()
            .form(form)
            .experiencedTechstack(memberExperiencedTechstackRepository
                .findTechstackByMemberName(form.getCompositeMemberClub().getMember()))
            .beginnerTechstack(memberBeginnerTechstackRepository
                .findTechstackByMemberName(form.getCompositeMemberClub().getMember()))
            .build();
    }

    // ?????? ??????
    @Transactional
    public HttpStatus approval(Long clubId, Long memberId) throws Exception {
        Club club = findClub(clubId);
        if (!SecurityUtil.getCurrentMemberId().equals(club.getMember().getId())) {
            throw new Exception("?????? ????????? ????????????");
        }
        List<Member> memberList = findMemberInClub(club);
        Member member = findMember(memberId);

        for (Member mem : memberList) {
            if (mem.getId().equals(memberId)) {
                throw new Exception("?????? ?????????????????? ???????????????.");
            }
        }

        addMember(club, member);
        reject(clubId, memberId);

        return HttpStatus.OK;
    }

    //     ????????? ??????
    @Transactional
    public HttpStatus reject(Long clubId, Long memberId) throws Exception {
        Club club = findClub(clubId);

        if (!SecurityUtil.getCurrentMemberId().equals(club.getMember().getId())
            && !SecurityUtil.getCurrentMemberId().equals(memberId)) {
            throw new Exception("?????? ????????? ????????????");
        }

        clubApplicationFormRepository.delete(clubApplicationFormRepository
            .findById(new CompositeMemberClub(findMember(memberId), club))
            .orElseThrow(() -> new NullPointerException("???????????? ?????? ??????????????????.")));

        return HttpStatus.OK;
    }

}
