package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.MemberClub;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.repository.MemberClubRepository;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.MemberDto;
import com.ssafy.match.group.dto.club.ClubDto;
import com.ssafy.match.group.dto.project.FormRegisterRequestDto;
import com.ssafy.match.group.dto.project.FormtInfoForRegisterResponseDto;
import com.ssafy.match.group.dto.project.FormtInfoResponseDto;
import com.ssafy.match.group.dto.project.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.project.ProjectMemberRoleResponseDto;
import com.ssafy.match.group.dto.project.ProjectUpdateRequestDto;
import com.ssafy.match.group.dto.study.Request.StudyCreateRequestDto;
import com.ssafy.match.group.dto.study.Request.StudyUpdateRequestDto;
import com.ssafy.match.group.dto.study.Response.StudyInfoForCreateResponseDto;
import com.ssafy.match.group.dto.study.Response.StudyInfoResponseDto;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.project.CompositeMemberProject;
import com.ssafy.match.group.entity.project.CompositeProjectTechstack;
import com.ssafy.match.group.entity.project.MemberProject;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.entity.project.ProjectApplicationForm;
import com.ssafy.match.group.entity.project.ProjectTechstack;
import com.ssafy.match.group.entity.study.CompositeMemberStudy;
import com.ssafy.match.group.entity.study.CompositeStudyTechstack;
import com.ssafy.match.group.entity.study.MemberStudy;
import com.ssafy.match.group.entity.study.Study;
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

        if (study.getMember().getId() != memberId) {
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

        if (study.getMember().getId() != SecurityUtil.getCurrentMemberId()) {
            throw new Exception("권한이 없습니다.");
        }

        List<MemberStudy> memberStudys = memberStudyRepository.findMemberRelationInStudy(study);
        for (MemberStudy mem : memberStudys) {
            mem.deActivation();
        }

        study.setIsActive(false);

        return HttpStatus.OK;
    }

    // 현재 프로젝트 정보 리턴
    public StudyInfoResponseDto getInfo(Long studyId) throws Exception {
        Study study = findStudy(studyId);
        if (SecurityUtil.getCurrentMemberId() != study.getMember().getId()
            && study.getIsPublic() == false) {
            throw new Exception("비공개된 프로젝트입니다.");
        }

        StudyInfoResponseDto dto = new StudyInfoResponseDto(study);
        dto.setAllTechstack(allTechstackName());
        dto.setStudyTechstack(studyTechstackName(study));
        dto.setClubList(makeClubDtos(memberClubRepository.findClubByMember(study.getMember())));
        dto.setMemberDtos(makeMemberDtos(findMemberInProject(study)));
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

    // 현재 스터디에 속한 멤버 리스트
    public List<Member> findMemberInProject(Study study) {
        return memberStudyRepository.findMemberInProject(study);
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

        if (study.getMember().getId() == memberId) {
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
        return clubRepository.findById(clubId)
            .orElseThrow(() -> new NullPointerException("클럽 정보가 없습니다."));
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
        return dbFileRepository.findById(uuid)
            .orElseThrow(() -> new NullPointerException("파일 정보가 없습니다."));
    }

    // 신청 버튼 클릭시 관련 정보 및 권한 체크
    public FormtInfoForRegisterResponseDto checkForRegister(Long projectId) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Project project = findProject(projectId);

        List<Member> memberList = memberInProject(projectId);
        for (Member mem : memberList) {
            if (SecurityUtil.getCurrentMemberId() == mem.getId()) {
                throw new Exception("이미 가입한 멤버입니다.");
            }
        }

        if (!project.isParticipate()) {
            throw new Exception("참여 불가능한 프로젝트입니다.");
        }

        List<String> allTechstack = allTechstackName();
        List<String> projectCity = Stream.of(City.values())
            .map(Enum::name)
            .collect(Collectors.toList());

        return FormtInfoForRegisterResponseDto.builder()
            .name(member.getName())
            .position(member.getPosition())
            .allTechstack(allTechstack)
            .projectCity(projectCity)
            .build();
    }

    @Transactional
    public HttpStatus createForm(Long projectId, FormRegisterRequestDto dto) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Project project = findProject(projectId);

        CompositeMemberProject mp = new CompositeMemberProject(member, project);

        Optional<ProjectApplicationForm> form = projectApplicationFormRepository.findById(mp);
        if (form.isPresent()) {
            throw new Exception("신청한 내역이 존재합니다.");
        }

        ProjectApplicationForm projectApplicationForm = ProjectApplicationForm.builder()
            .compositeMemberProject(mp)
            .nickname(dto.getNickname())
            .city(City.from(dto.getCity()))
            .role(dto.getRole())
            .position(dto.getPosition())
            .git(dto.getGit())
            .twitter(dto.getTwitter())
            .facebook(dto.getFacebook())
            .backjoon(dto.getBackjoon())
            .bio(dto.getBio())
            .createDate(LocalDateTime.now())
            .build();

        if (dto.getGit() != null) {
            projectApplicationForm.setGit(dto.getGit());
        }
        if (dto.getTwitter() != null) {
            projectApplicationForm.setTwitter(dto.getTwitter());
        }
        if (dto.getFacebook() != null) {
            projectApplicationForm.setFacebook(dto.getFacebook());
        }
        if (dto.getGit() != null) {
            projectApplicationForm.setGit(dto.getGit());
        }
        if (dto.getDbFile() != null) {
            projectApplicationForm.setDbFile(dto.getDbFile());
        }

        projectApplicationFormRepository.save(projectApplicationForm);
        return HttpStatus.OK;
    }

    // 모든 신청서 작성일 기준 내림차순 조회
    public List<FormtInfoResponseDto> allProjectForm(Long projectId) throws Exception {
        Project project = findProject(projectId);

        if (SecurityUtil.getCurrentMemberId() != project.getMember().getId()) {
            throw new Exception("조회 권한이 없습니다.");
        }

        List<ProjectApplicationForm> forms = projectApplicationFormRepository
            .formByProjectId(project);
        List<FormtInfoResponseDto> formtInfoResponseDtos = new ArrayList<>();

        for (ProjectApplicationForm form : forms) {
            formtInfoResponseDtos.add(new FormtInfoResponseDto(form));
        }

        return formtInfoResponseDtos;
    }

    // 닉네임으로 신청서 검색
    public List<FormtInfoResponseDto> allFormByProjectNickname(Long projectId, String nickname)
        throws Exception {
        Project project = findProject(projectId);

        if (SecurityUtil.getCurrentMemberId() != project.getMember().getId()) {
            throw new Exception("조회 권한이 없습니다.");
        }

        List<ProjectApplicationForm> forms = projectApplicationFormRepository
            .allFormByProjectNickname(project, nickname);
        List<FormtInfoResponseDto> formtInfoResponseDtos = new ArrayList<>();

        for (ProjectApplicationForm form : forms) {
            formtInfoResponseDtos.add(new FormtInfoResponseDto(form));
        }

        return formtInfoResponseDtos;
    }

    // 신청서 목록의 복합 기본키를 가져와 해당 신청서 상세조회 (프론트 방식에 따라 불필요할 수 있음)
    public FormtInfoResponseDto oneProjectForm(Long projectId, Long memberId) throws Exception {
        CompositeMemberProject cmp = new CompositeMemberProject(findMember(memberId),
            findProject(projectId));

        return projectApplicationFormRepository.oneFormById(cmp)
            .map(FormtInfoResponseDto::new)
            .orElseThrow(() -> new NullPointerException("존재하지 않는 신청서입니다"));
    }

    // 가입 승인
    @Transactional
    public HttpStatus approval(Long projectId, Long memberId) throws Exception {
        List<Member> members = memberInProject(projectId);
        Project project = findProject(projectId);
        Member member = findMember(memberId);

        for (Member mem : members) {
            if (mem.equals(member)) {
                throw new Exception("이미 가입되어있는 회원입니다.");
            }
        }

        ProjectApplicationForm form = projectApplicationFormRepository
            .findById(new CompositeMemberProject(findMember(memberId), findProject(projectId)))
            .orElseThrow(() -> new NullPointerException("존재하지 않는 신청서입니다."));

        addMember(project, memberId, form.getRole());
        reject(projectId, memberId);

        return HttpStatus.OK;
    }

//     신청서 제거
//    @Transactional
//    public HttpStatus reject(Long projectId, Long memberId) throws Exception {
//        projectApplicationFormRepository.delete(projectApplicationFormRepository
//            .findById(new CompositeMemberProject(findMember(memberId), findProject(projectId)))
//            .orElseThrow(() -> new NullPointerException("존재하지 않는 신청서입니다.")));
//
//        return HttpStatus.OK;
//    }
}
