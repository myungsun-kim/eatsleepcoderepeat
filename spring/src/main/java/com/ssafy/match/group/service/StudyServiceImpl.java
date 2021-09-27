package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.MemberSns;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.repository.MemberBeginnerTechstackRepository;
import com.ssafy.match.db.repository.MemberClubRepository;
import com.ssafy.match.db.repository.MemberExperiencedTechstackRepository;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.MemberSnsRepository;
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
import com.ssafy.match.group.dto.study.response.StudyInfoResponseDto;
import com.ssafy.match.group.entity.club.Club;
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

    // 스터디 생성을 위한 정보(기술 스택 리스트, 호스트의 클럽 정보, 지역 리스트)
    public StudyInfoForCreateResponseDto getInfoForCreate() throws Exception {
        return StudyInfoForCreateResponseDto.builder()
            .allTechstack(allTechstackName())
            .hostClub(memberClubRepository
                .findClubIdNameByMember(findMember(SecurityUtil.getCurrentMemberId())))
            .city(Stream.of(City.values())
                .map(Enum::name)
                .collect(Collectors.toList()))
            .build();

    }

    @Transactional
    public Long create(StudyCreateRequestDto dto) throws Exception {
        Study study = new Study(dto);
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        study.setMember(member);
        study.setClub(findClub(dto.getClubId()));
        study.setDBFile(findDBFile(dto.getUuid()));
        studyRepository.save(study);

        addTechstack(study, dto.getTechList());
        addMember(study, member);

        return study.getId();
    }

    @Transactional
    public HttpStatus update(Long studyId, StudyUpdateRequestDto dto) throws Exception {
        Study study = findStudy(studyId);
        Long memberId = SecurityUtil.getCurrentMemberId();
        if (!study.getMember().getId().equals(memberId)) {
            throw new Exception("권한이 없습니다.");
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
            throw new Exception("권한이 없습니다.");
        }

        List<MemberStudy> memberStudys = memberStudyRepository.findMemberRelationInStudy(study);
        for (MemberStudy mem : memberStudys) {
            mem.deActivation();
        }
        // 스터디 기술 스택 삭제 기능 추가필요
        study.setIsActive(false);

        return HttpStatus.OK;
    }

    // 현재 프로젝트 정보 리턴
    public StudyInfoResponseDto getInfo(Long studyId) throws Exception {
        Study study = findStudy(studyId);
        if (!SecurityUtil.getCurrentMemberId().equals(study.getMember().getId())
            && !study.getIsPublic()) {
            throw new Exception("비공개된 프로젝트입니다.");
        }

        StudyInfoResponseDto dto = new StudyInfoResponseDto(study);
        dto.setAllTechstack(allTechstackName());
        dto.setStudyTechstack(studyTechstackName(study));
        dto.setClubList(makeClubDtos(memberClubRepository.findClubByMember(study.getMember())));
        dto.setMemberDtos(makeMemberDtos(findMemberInStudy(study)));
        dto.setCityList(Stream.of(City.values())
            .map(Enum::name)
            .collect(Collectors.toList()));
        dto.setStatusList(Stream.of(Status.values())
            .map(Enum::name)
            .collect(Collectors.toList()));

        if (study.getClub() != null) {
            dto.setClub(new ClubDto(study.getClub()));
        }
        if (study.getDbFile() != null) {
            dto.setDbFile(study.getDbFile());
        }

        return dto;
    }

    // 모든 기술스택의 이름 리스트
    public List<String> allTechstackName() {
        return techstackRepository.findAllName();
    }

    // 현재 스터디 기술 스택의 이름 리스트
    public List<String> studyTechstackName(Study study) {
        return studyTechstackRepository.findByStudyTechstackName(study);
    }

    public Techstack findTechstack(String techName) {
        return techstackRepository.findByName(techName)
            .orElseThrow(() -> new NullPointerException("기술 스택 정보가 없습니다."));
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
                .orElseThrow(() -> new NullPointerException("제거할 기술 스택이 존재하지 않습니다."));

            studyTechstackRepository.delete(studyTechstack);
        }
    }

    @Transactional
    public void addMember(Study study, Member member) {
        CompositeMemberStudy compositeMemberStudy = new CompositeMemberStudy(member, study);

        // DB에 해당 멤버 기록이 없다면 새로 생성
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
    public void removeMember(Long studyId, Long memberId) throws Exception {
        Study study = findStudy(studyId);
        Member member = findMember(memberId);

        if (study.getMember().getId().equals(memberId)) {
            throw new Exception("프로젝트장은 탈퇴할 수 없습니다.");
        }

        CompositeMemberStudy compositeMemberStudy = new CompositeMemberStudy(member, study);

        MemberStudy memberStudy = memberStudyRepository.findById(compositeMemberStudy)
            .orElseThrow(() -> new NullPointerException("가입 기록이 없습니다."));

        memberStudy.deActivation();
        study.removeMember();
    }

    public Study findStudy(Long studyId) throws Exception {
        Study study = studyRepository.findById(studyId)
            .orElseThrow(() -> new NullPointerException("프로젝트 정보가 없습니다."));

        if (study.getIsActive() == false) {
            throw new Exception("삭제된 프로젝트입니다.");
        }

        return study;
    }

    public Member findMember(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NullPointerException("회원 정보가 없습니다."));

        if (member.getIs_active() == false) {
            throw new Exception("삭제된 멤버입니다.");
        }

        return member;
    }

    public Club findClub(Long clubId) {
        if(clubId == null){
            return null;
        }
        return clubRepository.findById(clubId)
            .orElseThrow(() -> new NullPointerException("클럽 정보가 없습니다."));
    }

    // 현재 스터디에 속한 멤버 리스트
    public List<Member> findMemberInStudy(Study study) {
        return memberStudyRepository.findMemberInStudy(study);
    }

    // 특정 멤버의 활성화 스터디 리스트
    public List<Study> studyInMember(Member member) {
        return memberStudyRepository.studyInMember(member);
    }

    public List<ClubDto> makeClubDtos(List<Club> hostClub){
        List<ClubDto> clubDtos = new ArrayList<>();

        for (Club club: hostClub) {
            clubDtos.add(new ClubDto(club));
        }

        return clubDtos;
    }

    public List<MemberDto> makeMemberDtos(List<Member> members){
        List<MemberDto> memberDtos = new ArrayList<>();

        for (Member member: members) {
            memberDtos.add(new MemberDto(member));
        }

        return memberDtos;
    }

    public DBFile findDBFile(String uuid) {
        if(uuid == null){
            return null;
        }
        return dbFileRepository.findById(uuid)
            .orElseThrow(() -> new NullPointerException("파일 정보가 없습니다."));
    }

    // 신청 버튼 클릭시 관련 정보 및 권한 체크
    public InfoForApplyStudyFormResponseDto getInfoForApply(Long studyId) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Study study = findStudy(studyId);

        List<Member> memberList = findMemberInStudy(study);
        for (Member mem : memberList) {
            if (SecurityUtil.getCurrentMemberId().equals(mem.getId())) {
                throw new Exception("이미 가입한 멤버입니다.");
            }
        }

        if (!study.getIsParticipate()) {
            throw new Exception("참여 불가능한 프로젝트입니다.");
        }

        InfoForApplyStudyFormResponseDto dto = InfoForApplyStudyFormResponseDto.builder()
            .name(member.getName())
            .strong(memberExperiencedTechstackRepository.findTechstackByMemberName(member))
            .knowledgeable(memberBeginnerTechstackRepository.findTechstackByMemberName(member))
            .projectCity(Stream.of(City.values())
                .map(Enum::name)
                .collect(Collectors.toList()))
            .build();

        Optional<MemberSns> git = memberSnsRepository.findByMemberAndSnsName(member, "git");
        Optional<MemberSns> twitter = memberSnsRepository.findByMemberAndSnsName(member, "twitter");
        Optional<MemberSns> facebook = memberSnsRepository.findByMemberAndSnsName(member, "facebook");
        Optional<MemberSns> backjoon = memberSnsRepository.findByMemberAndSnsName(member, "backjoon");

        if(git.isPresent()) dto.setGit(git.get().getSnsAccount());
        if(twitter.isPresent()) dto.setTwitter(twitter.get().getSnsAccount());
        if(facebook.isPresent()) dto.setFacebook(facebook.get().getSnsAccount());
        if(backjoon.isPresent()) dto.setBackjoon(backjoon.get().getSnsAccount());

        return dto;
    }

    @Transactional
    public HttpStatus applyStudy(Long studyId, StudyApplicationRequestDto dto) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Study study = findStudy(studyId);

        CompositeMemberStudy cmp = new CompositeMemberStudy(member, study);

        Optional<StudyApplicationForm> form = studyApplicationFormRepository.findById(cmp);
        if (form.isPresent()) {
            throw new Exception("신청한 내역이 존재합니다.");
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

        if (dto.getDbFile() != null) {
            studyApplicationForm.setDbFile(dto.getDbFile());
        }

        studyApplicationFormRepository.save(studyApplicationForm);
        return HttpStatus.OK;
    }

    // 모든 신청서 작성일 기준 내림차순 조회
    public List<StudyFormInfoResponseDto> allStudyForm(Long studyId) throws Exception {
        Study study = findStudy(studyId);

        if (SecurityUtil.getCurrentMemberId().equals(study.getMember().getId())) {
            throw new Exception("조회 권한이 없습니다.");
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

    // 닉네임으로 신청서 검색
    public List<StudyFormInfoResponseDto> allFormByStudyNickname(Long studyId, String nickname) throws Exception {
        Study study = findStudy(studyId);

        if (SecurityUtil.getCurrentMemberId().equals(study.getMember().getId())) {
            throw new Exception("조회 권한이 없습니다.");
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

    // 신청서 목록의 복합 기본키를 가져와 해당 신청서 상세조회 (프론트 방식에 따라 불필요할 수 있음)
    public StudyFormInfoResponseDto oneStudyForm(Long studyId, Long memberId) throws Exception {
        CompositeMemberStudy cms = new CompositeMemberStudy(findMember(memberId), findStudy(studyId));

        StudyApplicationForm form = studyApplicationFormRepository.oneFormById(cms)
            .orElseThrow(() -> new NullPointerException("존재하지 않는 신청서입니다"));

        return StudyFormInfoResponseDto.builder()
            .form(form)
            .strong(memberExperiencedTechstackRepository
                .findTechstackByMemberName(form.getCompositeMemberStudy().getMember()))
            .knowledgeable(memberBeginnerTechstackRepository
                .findTechstackByMemberName(form.getCompositeMemberStudy().getMember()))
            .build();


    }

    // 가입 승인
    @Transactional
    public HttpStatus approval(Long studyId, Long memberId) throws Exception {
        Study study = findStudy(studyId);
        List<Member> members = findMemberInStudy(study);
        Member member = findMember(memberId);

        for (Member mem : members) {
            if (mem.equals(member)) {
                throw new Exception("이미 가입되어있는 회원입니다.");
            }
        }

        addMember(study, member);
        reject(studyId, memberId);

        return HttpStatus.OK;
    }

//     신청서 제거
    @Transactional
    public HttpStatus reject(Long studyId, Long memberId) throws Exception {
        studyApplicationFormRepository.delete(studyApplicationFormRepository
            .findById(new CompositeMemberStudy(findMember(memberId), findStudy(studyId)))
            .orElseThrow(() -> new NullPointerException("존재하지 않는 신청서입니다.")));

        return HttpStatus.OK;
    }
}