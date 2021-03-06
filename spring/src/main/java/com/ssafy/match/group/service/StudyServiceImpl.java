package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.studyboard.board.entity.StudyBoard;
import com.ssafy.match.group.studyboard.board.repository.StudyBoardRepository;
import com.ssafy.match.member.entity.Member;
import com.ssafy.match.member.entity.MemberSns;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.member.repository.MemberBeginnerTechstackRepository;
import com.ssafy.match.group.repository.club.MemberClubRepository;
import com.ssafy.match.member.repository.MemberExperiencedTechstackRepository;
import com.ssafy.match.member.repository.MemberRepository;
import com.ssafy.match.member.repository.MemberSnsRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.MemberDto;
import com.ssafy.match.group.dto.club.ClubDto;
import com.ssafy.match.group.dto.study.request.StudyApplicationRequestDto;
import com.ssafy.match.group.dto.study.request.StudyCreateRequestDto;
import com.ssafy.match.group.dto.study.request.StudyUpdateRequestDto;
import com.ssafy.match.group.dto.study.response.InfoForApplyStudyFormResponseDto;
import com.ssafy.match.group.dto.study.response.StudyFormInfoResponseDto;
import com.ssafy.match.group.dto.study.response.StudyInfoForCreateResponseDto;
import com.ssafy.match.group.dto.study.response.StudyInfoForUpdateResponseDto;
import com.ssafy.match.group.dto.study.response.StudyInfoResponseDto;
import com.ssafy.match.group.entity.study.CompositeMemberStudy;
import com.ssafy.match.group.entity.study.CompositeStudyTechstack;
import com.ssafy.match.group.entity.study.MemberStudy;
import com.ssafy.match.group.entity.study.Study;
import com.ssafy.match.group.entity.study.StudyApplicationForm;
import com.ssafy.match.group.entity.study.StudyTechstack;
import com.ssafy.match.group.repository.club.ClubRepository;
import com.ssafy.match.group.repository.study.MemberStudyRepository;
import com.ssafy.match.group.repository.study.StudyApplicationFormRepository;
import com.ssafy.match.group.repository.study.StudyRepository;
import com.ssafy.match.group.repository.study.StudyTechstackRepository;
import com.ssafy.match.util.SecurityUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
// https://velog.io/@kdhyo/JavaTransactional-Annotation-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90-26her30h
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;
    private final ClubRepository clubRepository;
    private final MemberStudyRepository memberStudyRepository;
    private final MemberClubRepository memberClubRepository;
    private final StudyApplicationFormRepository studyApplicationFormRepository;
    private final TechstackRepository techstackRepository;
    private final StudyTechstackRepository studyTechstackRepository;
    private final DBFileRepository dbFileRepository;
    private final MemberExperiencedTechstackRepository memberExperiencedTechstackRepository;
    private final MemberBeginnerTechstackRepository memberBeginnerTechstackRepository;
    private final MemberSnsRepository memberSnsRepository;
    private final StudyBoardRepository studyBoardRepository;

    // ????????? ????????? ?????? ??????(???????????? ?????? ??????)
    public StudyInfoForCreateResponseDto getInfoForCreate() throws Exception {
        return StudyInfoForCreateResponseDto.builder()
            .hostClub(makeClubDtos(memberClubRepository
                .findClubByMember(findMember(SecurityUtil.getCurrentMemberId()))))
            .build();
    }

    @Transactional
    public Long create(StudyCreateRequestDto dto) throws Exception {
        validCity(dto.getCity());
        validTechstack(dto.getTechList());

        Study study = new Study(dto);
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        study.setMember(member);
        study.setClub(findClub(dto.getClubId()));
        study.setDBFile(findDBFile(dto.getCoverpic_uuid()));

        studyRepository.save(study);
        makeBasicBoards(study);
        addTechstack(study, dto.getTechList());
        addMember(study, member);

        return study.getId();
    }

    @Transactional
    public HttpStatus update(Long studyId, StudyUpdateRequestDto dto) throws Exception {
        validCity(dto.getCity());
        validTechstack(dto.getAddStackList());
        validTechstack(dto.getRemoveStackList());
        validStatus(dto.getStatus());

        Study study = findStudy(studyId);
        Long memberId = SecurityUtil.getCurrentMemberId();
        if (!study.getMember().getId().equals(memberId)) {
            throw new Exception("????????? ????????????.");
        }

        study.update(dto);
        study.setMember(findMember(dto.getHostId()));
        study.setClub(findClub(dto.getClubId()));
        study.setDBFile(findDBFile(dto.getUuid()));
        addTechstack(study, dto.getAddStackList());
        removeTechstack(study, dto.getRemoveStackList());

        return HttpStatus.OK;
    }

    @Transactional
    public HttpStatus delete(Long studyId) throws Exception {
        Study study = findStudy(studyId);

        if (!study.getMember().getId().equals(SecurityUtil.getCurrentMemberId())) {
            throw new Exception("????????? ????????????.");
        }

        List<MemberStudy> memberStudys = memberStudyRepository.findMemberRelationInStudy(study);
        for (MemberStudy mem : memberStudys) {
            mem.deActivation();
        }

        List<StudyTechstack> sts = studyTechstackRepository.findStudyTechstackByStudy(study);
        for (StudyTechstack st: sts) {
            studyTechstackRepository.delete(st);
        }

        study.setIsActive(false);

        return HttpStatus.OK;
    }

    public Page<StudyInfoResponseDto> getAllStudy(Pageable pageable) {
        Page<StudyInfoResponseDto> studyInfoResponseDtos = studyRepository.findByIsActiveAndIsPublicAndStatusIsNot(Boolean.TRUE, Boolean.TRUE, Status.??????, pageable)
                .map(StudyInfoResponseDto::of);
        for (StudyInfoResponseDto studyInfoResponseDto: studyInfoResponseDtos.getContent()) {
            studyInfoResponseDto.setMemberDtos(makeMemberDtos(memberStudyRepository.findMemberByStudyId(studyInfoResponseDto.getId())));
            studyInfoResponseDto.setTechList(studyTechstackRepository.findStudyTechstackNameByStudyId(studyInfoResponseDto.getId()));
        }
        return studyInfoResponseDtos;
    }

    // ?????? ????????? ?????? ??????
    public StudyInfoResponseDto getOneStudy(Long studyId) throws Exception {
        Study study = findStudy(studyId);

        if (!SecurityUtil.getCurrentMemberId().equals(study.getMember().getId())
            && !study.getIsPublic()) {
            throw new Exception("???????????? ??????????????????.");
        }
        StudyInfoResponseDto studyInfoResponseDto = studyRepository.findById(studyId).map(StudyInfoResponseDto::of).orElseThrow(() -> new NullPointerException("???????????? ????????????."));
        studyInfoResponseDto.setMemberDtos(makeMemberDtos(memberStudyRepository.findMemberByStudyId(studyInfoResponseDto.getId())));
        studyInfoResponseDto.setTechList(studyTechstackRepository.findStudyTechstackNameByStudyId(studyInfoResponseDto.getId()));
        return studyInfoResponseDto;
    }

    // ????????? ??????????????? ?????? ??????(?????? ?????? + ?????? ?????? ?????????, ???????????? ?????? ??????, ?????? ?????????)
    public StudyInfoForUpdateResponseDto getInfoForUpdateStudy(Long studyId) throws Exception {
        Study study = findStudy(studyId);
        if (!SecurityUtil.getCurrentMemberId().equals(study.getMember().getId())) {
            throw new Exception("????????? ????????????");
        }

        StudyInfoForUpdateResponseDto dto = new StudyInfoForUpdateResponseDto(study);
        dto.setStudyTechstack(studyTechstackName(study));
        dto.setClubList(makeClubDtos(memberClubRepository.findClubByMember(study.getMember())));
        dto.setMemberDtos(makeMemberDtos(findMemberInStudy(study)));
        dto.setHost(new MemberDto(study.getMember()));

        return dto;
    }

    // ?????? ??????????????? ?????? ?????????
    public List<String> allTechstackName() {
        return techstackRepository.findAllName();
    }

    // ?????? ????????? ?????? ????????? ?????? ?????????
    public List<String> studyTechstackName(Study study) {
        return studyTechstackRepository.findByStudyTechstackName(study);
    }

    public Techstack findTechstack(String techName) {
        return techstackRepository.findByName(techName)
            .orElseThrow(() -> new NullPointerException("?????? ?????? ????????? ????????????."));
    }

    @Transactional
    public void addTechstack(Study study, List<String> techName) {
        for (String name : techName) {
            Techstack techstack = findTechstack(name);
            CompositeStudyTechstack compositeStudyTechstack = new CompositeStudyTechstack(techstack,
                study);

            studyTechstackRepository.save(new StudyTechstack(compositeStudyTechstack));
        }
    }

    @Transactional
    public void removeTechstack(Study study, List<String> techName) {
        for (String name : techName) {
            Techstack techstack = findTechstack(name);
            CompositeStudyTechstack compositeStudyTechstack = new CompositeStudyTechstack(techstack,
                study);

            StudyTechstack studyTechstack = studyTechstackRepository
                .findById(compositeStudyTechstack)
                .orElseThrow(() -> new NullPointerException("????????? ?????? ????????? ???????????? ????????????."));

            studyTechstackRepository.delete(studyTechstack);
        }
    }

    @Transactional
    public void addMember(Study study, Member member) {
        CompositeMemberStudy compositeMemberStudy = new CompositeMemberStudy(member, study);

        // DB??? ?????? ?????? ????????? ????????? ?????? ??????
        MemberStudy memberStudy = memberStudyRepository
            .findById(compositeMemberStudy)
            .orElseGet(() -> MemberStudy.builder()
                .compositeMemberStudy(compositeMemberStudy)
                .registerDate(LocalDateTime.now())
                .build());

        memberStudy.activation();
        study.addMember();

        memberStudyRepository.save(memberStudy);
    }

    @Transactional
    public HttpStatus removeMember(Long studyId) throws Exception {
        Study study = findStudy(studyId);
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new NullPointerException("????????? ????????? ?????????.(????????? ??????)"));
        if (study.getMember().getId().equals(member.getId())) {
            throw new Exception("??????????????? ????????? ??? ????????????.");
        }
        CompositeMemberStudy compositeMemberStudy = new CompositeMemberStudy(member, study);
        MemberStudy memberStudy = memberStudyRepository.findById(compositeMemberStudy)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));
        memberStudy.deActivation();
        study.removeMember();
        return HttpStatus.OK;
    }

    @Transactional
    public void makeBasicBoards(Study study){
        studyBoardRepository.save(new StudyBoard("????????????", study));
        studyBoardRepository.save(new StudyBoard("?????????", study));
    }

    public Study findStudy(Long studyId) throws Exception {
        Study study = studyRepository.findById(studyId)
            .orElseThrow(() -> new NullPointerException("????????? ????????? ????????????."));

        if (!study.getIsActive()) {
            throw new Exception("????????? ??????????????????.");
        }

        return study;
    }

    public Member findMember(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));

        if (!member.getIs_active()) {
            throw new Exception("????????? ???????????????.");
        }

        return member;
    }

    public Club findClub(Long clubId) {
        if (clubId == null) {
            return null;
        }
        return clubRepository.findById(clubId)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));
    }

    public DBFile findDBFile(String uuid) {
        if (uuid == null) {
            return null;
        }
        return dbFileRepository.findById(uuid)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));
    }

    // ?????? ???????????? ?????? ?????? ?????????
    public List<Member> findMemberInStudy(Study study) {
        return memberStudyRepository.findMemberInStudy(study);
    }

    // ?????? ????????? ????????? ????????? ?????????
    public List<Study> studyInMember(Member member) {
        return memberStudyRepository.studyInMember(member);
    }

    public void validCity(String city) throws Exception {
        if(!Stream.of(City.values()).map(Enum::name)
            .collect(Collectors.toList()).contains(city)){
            throw new Exception("???????????? ?????? ???????????????");
        }
    }
    public void validStatus(String status) throws Exception {
        if(!Stream.of(Status.values()).map(Enum::name)
            .collect(Collectors.toList()).contains(status)){
            throw new Exception("???????????? ?????? ???????????????");
        }
    }
    public void validTechstack(List<String> techstacks) throws Exception {
        for (String inTech: techstacks) {
            if(!techstackRepository.findAllName().contains(inTech)){
                throw new Exception("???????????? ?????? ?????? ???????????????");
            }
        }
    }

    public List<ClubDto> makeClubDtos(List<Club> hostClub) {
        List<ClubDto> clubDtos = new ArrayList<>();

        for (Club club : hostClub) {
            clubDtos.add(new ClubDto(club));
        }

        return clubDtos;
    }


    public List<MemberDto> makeMemberDtos(List<Member> members) {
        List<MemberDto> memberDtos = new ArrayList<>();

        for (Member member : members) {
            memberDtos.add(new MemberDto(member));
        }

        return memberDtos;
    }

    // ?????? ?????? ????????? ?????? ?????? ??? ?????? ??????
    public InfoForApplyStudyFormResponseDto getInfoForApply(Long studyId) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Study study = findStudy(studyId);

        List<Member> memberList = findMemberInStudy(study);
        for (Member mem : memberList) {
            if (SecurityUtil.getCurrentMemberId().equals(mem.getId())) {
                throw new Exception("?????? ????????? ???????????????.");
            }
        }

        if (!study.getIsParticipate()) {
            throw new Exception("?????? ???????????? ??????????????????.");
        }

        InfoForApplyStudyFormResponseDto dto = InfoForApplyStudyFormResponseDto.builder()
            .nickname(member.getNickname())
            .strong(memberExperiencedTechstackRepository.findTechstackByMemberName(member))
            .knowledgeable(memberBeginnerTechstackRepository.findTechstackByMemberName(member))
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
    public HttpStatus applyStudy(Long studyId, StudyApplicationRequestDto dto) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Study study = findStudy(studyId);

        CompositeMemberStudy cmp = new CompositeMemberStudy(member, study);

        Optional<StudyApplicationForm> form = studyApplicationFormRepository.findById(cmp);
        if (form.isPresent()) {
            throw new Exception("????????? ????????? ???????????????.");
        }

        StudyApplicationForm studyApplicationForm = new StudyApplicationForm(cmp, dto);

        if (dto.getGit() != null) {
            studyApplicationForm.setGit(dto.getGit());
        }
        if (dto.getTwitter() != null) {
            studyApplicationForm.setTwitter(dto.getTwitter());
        }
        if (dto.getFacebook() != null) {
            studyApplicationForm.setFacebook(dto.getFacebook());
        }
        if(dto.getBackjoon() != null){
            studyApplicationForm.setBackjoon(dto.getBackjoon());
        }

        studyApplicationForm.setDbFile(findDBFile(dto.getUuid()));

        studyApplicationFormRepository.save(studyApplicationForm);
        return HttpStatus.OK;
    }

    // ?????? ????????? ????????? ?????? ???????????? ??????
    public List<StudyFormInfoResponseDto> getAllStudyForm(Long studyId) throws Exception {
        Study study = findStudy(studyId);

        if (!SecurityUtil.getCurrentMemberId().equals(study.getMember().getId())) {
            throw new Exception("?????? ????????? ????????????.");
        }

        List<StudyApplicationForm> forms = studyApplicationFormRepository.formByStudyId(study);
        List<StudyFormInfoResponseDto> studyFormInfoResponseDtos = new ArrayList<>();

        for (StudyApplicationForm form : forms) {
            studyFormInfoResponseDtos.add(StudyFormInfoResponseDto.builder()
                .form(form)
                .strong(memberExperiencedTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberStudy().getMember()))
                .knowledgeable(memberBeginnerTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberStudy().getMember()))
                .build());
        }

        return studyFormInfoResponseDtos;
    }

    // ??????????????? ????????? ??????
    public List<StudyFormInfoResponseDto> getAllFormByStudyNickname(Long studyId, String nickname)
        throws Exception {
        Study study = findStudy(studyId);

        if (!SecurityUtil.getCurrentMemberId().equals(study.getMember().getId())) {
            throw new Exception("?????? ????????? ????????????.");
        }

        List<StudyApplicationForm> forms = studyApplicationFormRepository
            .allFormByStudyNickname(study, nickname);
        List<StudyFormInfoResponseDto> studyFormInfoResponseDtos = new ArrayList<>();

        for (StudyApplicationForm form : forms) {
            studyFormInfoResponseDtos.add(StudyFormInfoResponseDto.builder()
                .form(form)
                .strong(memberExperiencedTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberStudy().getMember()))
                .knowledgeable(memberBeginnerTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberStudy().getMember()))
                .build());
        }

        return studyFormInfoResponseDtos;
    }

    // ????????? ????????? ?????? ???????????? ????????? ?????? ????????? ????????????
    public StudyFormInfoResponseDto getOneStudyForm(Long studyId, Long memberId) throws Exception {
        CompositeMemberStudy cms = new CompositeMemberStudy(findMember(memberId),
            findStudy(studyId));

        StudyApplicationForm form = studyApplicationFormRepository.oneFormById(cms)
            .orElseThrow(() -> new NullPointerException("???????????? ?????? ??????????????????"));

        return StudyFormInfoResponseDto.builder()
            .form(form)
            .strong(memberExperiencedTechstackRepository
                .findTechstackByMemberName(form.getCompositeMemberStudy().getMember()))
            .knowledgeable(memberBeginnerTechstackRepository
                .findTechstackByMemberName(form.getCompositeMemberStudy().getMember()))
            .build();
    }

    // ?????? ??????
    @Transactional
    public HttpStatus approval(Long studyId, Long memberId) throws Exception {
        Study study = findStudy(studyId);
        if (!SecurityUtil.getCurrentMemberId().equals(study.getMember().getId())) {
            throw new Exception("?????? ????????? ????????????");
        }
        List<Member> members = findMemberInStudy(study);
        Member member = findMember(memberId);

        for (Member mem : members) {
            if (mem.equals(member)) {
                throw new Exception("?????? ?????????????????? ???????????????.");
            }
        }

        addMember(study, member);
        reject(studyId, memberId);

        return HttpStatus.OK;
    }

    //     ????????? ??????
    @Transactional
    public HttpStatus reject(Long studyId, Long memberId) throws Exception {
        Study study = findStudy(studyId);

        if (!SecurityUtil.getCurrentMemberId().equals(study.getMember().getId())
            && !SecurityUtil.getCurrentMemberId().equals(memberId)) {
            throw new Exception("?????? ????????? ????????????");
        }

        studyApplicationFormRepository.delete(studyApplicationFormRepository
            .findById(new CompositeMemberStudy(findMember(memberId), study))
            .orElseThrow(() -> new NullPointerException("???????????? ?????? ??????????????????.")));

        return HttpStatus.OK;
    }
}
