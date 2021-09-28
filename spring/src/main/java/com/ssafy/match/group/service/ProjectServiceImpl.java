package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
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
import com.ssafy.match.group.dto.project.request.ProjectApplicationRequestDto;
import com.ssafy.match.group.dto.project.request.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.project.request.ProjectUpdateRequestDto;
import com.ssafy.match.group.dto.project.response.InfoForApplyProjectFormResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectFormtInfoResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectInfoForCreateResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectMemberRoleResponseDto;
import com.ssafy.match.group.dto.study.response.StudyInfoResponseDto;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.project.CompositeMemberProject;
import com.ssafy.match.group.entity.project.CompositeProjectTechstack;
import com.ssafy.match.group.entity.project.MemberProject;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.entity.project.ProjectApplicationForm;
import com.ssafy.match.group.entity.project.ProjectTechstack;
import com.ssafy.match.group.entity.study.Study;
import com.ssafy.match.group.repository.club.ClubRepository;
import com.ssafy.match.group.repository.project.MemberProjectRepository;
import com.ssafy.match.group.repository.project.ProjectApplicationFormRepository;
import com.ssafy.match.group.repository.project.ProjectRepository;
import com.ssafy.match.group.repository.project.ProjectTechstackRepository;
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
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final ClubRepository clubRepository;
    private final MemberClubRepository memberClubRepository;
    private final MemberProjectRepository memberProjectRepository;
    private final ProjectApplicationFormRepository projectApplicationFormRepository;
    private final TechstackRepository techstackRepository;
    private final ProjectTechstackRepository projectTechstackRepository;
    private final DBFileRepository dbFileRepository;
    private final MemberExperiencedTechstackRepository memberExperiencedTechstackRepository;
    private final MemberBeginnerTechstackRepository memberBeginnerTechstackRepository;
    private final MemberSnsRepository memberSnsRepository;

    public ProjectInfoForCreateResponseDto getInfoForCreate() throws Exception {
        return ProjectInfoForCreateResponseDto.builder()
            .hostClub(memberClubRepository
                .findClubIdNameByMember(findMember(SecurityUtil.getCurrentMemberId())))
            .build();
    }
    @Transactional
    public Long create(ProjectCreateRequestDto dto) throws Exception {
        validCity(dto.getCity());
        validTechstack(dto.getTechList());

        Project project = new Project(dto);
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        project.setMember(member);
        project.setClub(findClub(dto.getClubId()));
        project.setDBFile(findDBFile(dto.getUuid()));

        projectRepository.save(project);

        addTechstack(project, dto.getTechList());
        addMember(project, member, dto.getHostRole());

        return project.getId();
    }

    @Transactional
    public HttpStatus update(Long projectId, ProjectUpdateRequestDto dto) throws Exception {
        validCity(dto.getCity());
        validTechstack(dto.getAddStackList());
        validTechstack(dto.getRemoveStackList());
        validStatus(dto.getStatus());

        Project project = findProject(projectId);
        Long currentMemberId = SecurityUtil.getCurrentMemberId();

        if (!project.getMember().getId().equals(currentMemberId)) {
            throw new Exception("권한이 없습니다.");
        }

        project.update(dto);
        project.setMember(findMember(dto.getHostId()));
        project.setClub(findClub(dto.getClubId()));
        project.setDBFile(findDBFile(dto.getUuid()));
        addTechstack(project, dto.getAddStackList());
        removeTechstack(project, dto.getRemoveStackList());

        return HttpStatus.OK;
    }

    @Transactional
    public HttpStatus delete(Long projectId) throws Exception {
        Project project = findProject(projectId);

        if (project.getMember().getId() != SecurityUtil.getCurrentMemberId()) {
            throw new Exception("권한이 없습니다.");
        }

        List<MemberProject> memberProjects = memberProjectRepository.findMemberWithProject(project);
        for (MemberProject mem : memberProjects) {
            mem.deactivation();
        }

        List<ProjectTechstack> pts = projectTechstackRepository.findProjectTechstackByProject(project);
        for (ProjectTechstack pt: pts) {
            projectTechstackRepository.delete(pt);
        }

        project.setIsActive(false);

        return HttpStatus.OK;
    }

    public List<StudyInfoResponseDto> getAllStudy() {
        List<Study> studies = studyRepository.findAllStudy();
        List<StudyInfoResponseDto> studyInfoResponseDtos = new ArrayList<>();

        for (Study study: studies) {
            if(study.getStatus().equals(Status.종료)) continue;
            StudyInfoResponseDto dto = new StudyInfoResponseDto(study);
            dto.setMemberDtos(makeMemberDtos(findMemberInStudy(study)));
            studyInfoResponseDtos.add(dto);
        }

        return studyInfoResponseDtos;
    }

    // 현재 프로젝트 정보 리턴
    public ProjectInfoResponseDto projectInfo(Long projectId) throws Exception {

        Project project = findProject(projectId);
        if (SecurityUtil.getCurrentMemberId() != project.getMember().getId()
            && !project.getIsPublic()) {
            throw new Exception("비공개된 프로젝트입니다.");
        }

        List<String> developerNicknames = memberNicknames(projectId, "개발자");
        List<String> desiginerNicknames = memberNicknames(projectId, "디자이너");
        List<String> plannerNicknames = memberNicknames(projectId, "기획자");
        List<String> allTechstack = allTechstackName();
        List<String> projectTechstack = projectTechstackName(projectId);
        List<Club> hostClub = memberClubRepository.findClubByMember(project.getMember());

        List<Member> projectMember = memberInProject(projectId);
        List<ProjectMemberRoleResponseDto> projectMemberInfo = new ArrayList<>();
        for (Member mem: projectMember) {
            projectMemberInfo.add(new ProjectMemberRoleResponseDto(mem.getId(), mem.getName(), mem.getNickname()));
        }

        List<String> projectCity = Stream.of(City.values())
            .map(Enum::name)
            .collect(Collectors.toList());

        ProjectInfoResponseDto responseDto = ProjectInfoResponseDto.builder()
            .name(project.getName())
            .schedule(project.getSchedule())
            .period(project.getPeriod())
            .hostNickname(project.getMember().getNickname())
            .developerCount(project.getDeveloperCount())
            .developerNicknames(developerNicknames)
            .developerMaxCount(project.getDeveloperMaxCount())
            .designerCount(project.getDesignerCount())
            .designerNicknames(desiginerNicknames)
            .designerMaxCount(project.getDesignerMaxCount())
            .plannerCount(project.getPlannerCount())
            .plannerNicknames(plannerNicknames)
            .plannerMaxCount(project.getPlannerMaxCount())
            .isPublic(project.getIsPublic())
            .city(project.getCity())
            .status(project.getStatus())
            .isParticipate(project.getIsParticipate())
            .modifyDate(project.getModifyDate())
            .bio(project.getBio())
            .allTechstack(allTechstack)
            .projectTechstack(projectTechstack)
            .hostClub(hostClub)
            .projectMemberInfo(projectMemberInfo)
            .projectCity(projectCity)
            .build();

        if (project.getClub() != null) {
            responseDto.setClubId(project.getClub().getId());
            responseDto.setClubName(project.getClub().getName());
        }
        if (project.getDbFile() != null) {
            responseDto.setPicData(project.getDbFile().getData());
        }

        return responseDto;
    }

    // 현재 프로젝트에 어떤 멤버가 속해있는지 멤버 리스트 리턴
    public List<Member> memberInProject(Long projectId) throws Exception {
        return memberProjectRepository.memberInProject(findProject(projectId));
    }

    // 특정 프로젝트의 특정 역할인 멤버의 닉네임 리스트
    public List<String> memberNicknames(Long projectId, String role) throws Exception {
        return memberProjectRepository.findMemberNickname(findProject(projectId), role);
    }

    // 특정 멤버의 활성화 프로젝트 리스트
    public List<Project> projectInMember(Long memberId) throws Exception {
        return memberProjectRepository.projectInMember(findMember(memberId));
    }

    // 모든 기술스택의 이름 리스트
    public List<String> allTechstackName() {
        return techstackRepository.findAllName();
    }

    // 현재 프로젝트 기술 스택의 이름 리스트
    public List<String> projectTechstackName(Long projectId) throws Exception {
        return projectTechstackRepository.findByProjectTechstackName(findProject(projectId));
    }

    @Transactional
    public void addTechstack(Project project, List<String> techName) {
        for (String name : techName) {
            Techstack techstack = findTechstack(name);
            CompositeProjectTechstack compositeProjectTechstack = new CompositeProjectTechstack(
                techstack, project);

            projectTechstackRepository.save(new ProjectTechstack(compositeProjectTechstack));

        }
    }

    @Transactional
    public void removeTechstack(Project project, List<String> techName) {
        for (String name : techName) {
            Techstack techstack = findTechstack(name);
            CompositeProjectTechstack compositeProjectTechstack = new CompositeProjectTechstack(
                techstack, project);

            ProjectTechstack projectTechstack = projectTechstackRepository
                .findById(compositeProjectTechstack)
                .orElseThrow(() -> new NullPointerException("해당 기술 스택이 존재하지않습니다."));

            projectTechstackRepository.delete(projectTechstack);
        }
    }

    @Transactional
    public void addMember(Project project, Member member, String role) {
        CompositeMemberProject compositeMemberProject = new CompositeMemberProject(member, project);
        // DB에 해당 멤버 기록이 없다면 새로 생성
        MemberProject memberProject = memberProjectRepository
            .findById(compositeMemberProject)
            .orElseGet(() -> MemberProject.builder()
                .compositeMemberProject(compositeMemberProject)
                .registerDate(LocalDateTime.now())
                .build());

        memberProject.activation();
        memberProjectRepository.save(memberProject);

        changeRole(project, member, role);
    }

    @Transactional
    public void removeMember(Long projectId, Long memberId) throws Exception {
        Project project = findProject(projectId);
        Member member = findMember(memberId);

        if(project.getMember().getId().equals(memberId)){
            throw new Exception("프로젝트장은 탈퇴할 수 없습니다.");
        }

        CompositeMemberProject compositeMemberProject = new CompositeMemberProject(member, project);
        // DB에 해당 멤버 기록이 없다면 새로 생성
        MemberProject memberProject = memberProjectRepository.findById(compositeMemberProject)
            .orElseThrow(() -> new NullPointerException("이미 탈퇴된 멤버입니다."));

        memberProject.deactivation();
        changeRole(project, member, "");
    }

    public Project findProject(Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new NullPointerException("프로젝트 정보가 없습니다."));

        if (!project.getIsActive()) {
            throw new Exception("삭제된 프로젝트입니다.");
        }

        return project;
    }

    public Member findMember(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NullPointerException("회원 정보가 없습니다."));

        if (member.getIs_active() == false) {
            throw new Exception("삭제된 멤버입니다.");
        }

        return member;
    }

    public Techstack findTechstack(String techName) {
        return techstackRepository.findByName(techName)
            .orElseThrow(() -> new NullPointerException("기술 스택 정보가 없습니다."));
    }

    public Club findClub(Long clubId) {
        if (clubId == null) {
            return null;
        }
        return clubRepository.findById(clubId)
            .orElseThrow(() -> new NullPointerException("클럽 정보가 없습니다."));
    }

    public DBFile findDBFile(String uuid) {
        if (uuid == null) {
            return null;
        }
        return dbFileRepository.findById(uuid)
            .orElseThrow(() -> new NullPointerException("파일 정보가 없습니다."));
    }

    public void validCity(String city) throws Exception {
        if(!Stream.of(City.values()).map(Enum::name)
            .collect(Collectors.toList()).contains(city)){
            throw new Exception("존재하지 않는 지역입니다");
        }
    }
    public void validStatus(String status) throws Exception {
        if(!Stream.of(Status.values()).map(Enum::name)
            .collect(Collectors.toList()).contains(status)){
            throw new Exception("존재하지 않는 지역입니다");
        }
    }
    public void validTechstack(List<String> techstacks) throws Exception {
        for (String inTech: techstacks) {
            if(!techstackRepository.findAllName().contains(inTech)){
                throw new Exception("존재하지 않는 기술 스택입니다");
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

    // (아이디어가 생각이 안나서 임시로 If문 사용 조언 구함)
    @Transactional
    public void changeRole(Project project, Member member, String role) {
        MemberProject memberProject = memberProjectRepository.findMemberProject(project, member);

        String now = memberProject.getRole();

        if (now == null) { // 이제 생성된 프로젝트이거나 이제 가입한 경우
            if (role.equals("디자이너")) {
                project.plusDesigner();
                memberProject.setRole("디자이너");
            } else if (role.equals("개발자")) {
                project.plusDeveloper();
                memberProject.setRole("개발자");
            } else if (role.equals("기획자")) {
                project.plusPlanner();
                memberProject.setRole("기획자");
            }
        } else if (now.equals(role)) { // 변경사항이 없는 경우
            return;
        } else {
            if (now.equals("디자이너")) {
                project.minusDesigner();

                if (role.equals("개발자")) {
                    project.plusDeveloper();
                    memberProject.setRole("개발자");
                } else if (role.equals("기획자")) {
                    project.plusPlanner();
                    memberProject.setRole("기획자");
                }
            } else if (now.equals("개발자")) {
                project.minusDeveloper();

                if (role.equals("기획자")) {
                    project.plusPlanner();
                    memberProject.setRole("기획자");
                } else if (role.equals("디자이너")) {
                    project.plusDesigner();
                    memberProject.setRole("디자이너");
                }
            } else if (now.equals("기획자")) {
                project.minusPlanner();

                if (role.equals("개발자")) {
                    project.plusDeveloper();
                    memberProject.setRole("개발자");
                } else if (role.equals("디자이너")) {
                    project.plusDesigner();
                    memberProject.setRole("디자이너");
                }
            }
        }

        if (project.getMember().getId().equals(member.getId())) {
            project.setHostRole(role);
        }

    }

    // 신청 버튼 클릭시 관련 정보 및 권한 체크
    public InfoForApplyProjectFormResponseDto checkForRegister(Long projectId) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Project project = findProject(projectId);

        List<Member> memberList = memberInProject(projectId);
        for (Member mem: memberList) {
            if(SecurityUtil.getCurrentMemberId() == mem.getId()){
                throw new Exception("이미 가입한 멤버입니다.");
            }
        }

        if(!project.getIsParticipate()){
            throw new Exception("참여 불가능한 프로젝트입니다.");
        }

        List<String> allTechstack = allTechstackName();
        List<String> projectCity = Stream.of(City.values())
            .map(Enum::name)
            .collect(Collectors.toList());

        return InfoForApplyProjectFormResponseDto.builder()
            .name(member.getName())
            .position(member.getPosition())
            .allTechstack(allTechstack)
            .projectCity(projectCity)
            .build();
    }

    @Transactional
    public HttpStatus createForm(Long projectId, ProjectApplicationRequestDto dto) throws Exception {
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

        if(dto.getGit() != null){
            projectApplicationForm.setGit(dto.getGit());
        }
        if(dto.getTwitter() != null){
            projectApplicationForm.setTwitter(dto.getTwitter());
        }
        if(dto.getFacebook() != null){
            projectApplicationForm.setFacebook(dto.getFacebook());
        }
        if(dto.getGit() != null){
            projectApplicationForm.setGit(dto.getGit());
        }
        if (dto.getDbFile() != null) {
            projectApplicationForm.setDbFile(dto.getDbFile());
        }

        projectApplicationFormRepository.save(projectApplicationForm);
        return HttpStatus.OK;
    }

    // 모든 신청서 작성일 기준 내림차순 조회
    public List<ProjectFormtInfoResponseDto> allProjectForm(Long projectId) throws Exception {
        Project project = findProject(projectId);

        if(SecurityUtil.getCurrentMemberId() != project.getMember().getId()){
            throw new Exception("조회 권한이 없습니다.");
        }

        List<ProjectApplicationForm> forms = projectApplicationFormRepository.formByProjectId(project);
        List<ProjectFormtInfoResponseDto> projectFormtInfoResponseDtos = new ArrayList<>();

        for (ProjectApplicationForm form: forms) {
            projectFormtInfoResponseDtos.add(new ProjectFormtInfoResponseDto(form));
        }

        return projectFormtInfoResponseDtos;
    }

    // 닉네임으로 신청서 검색
    public List<ProjectFormtInfoResponseDto> allFormByProjectNickname(Long projectId, String nickname) throws Exception{
        Project project = findProject(projectId);

        if(SecurityUtil.getCurrentMemberId() != project.getMember().getId()){
            throw new Exception("조회 권한이 없습니다.");
        }

        List<ProjectApplicationForm> forms = projectApplicationFormRepository.allFormByProjectNickname(project, nickname);
        List<ProjectFormtInfoResponseDto> projectFormtInfoResponseDtos = new ArrayList<>();

        for (ProjectApplicationForm form: forms) {
            projectFormtInfoResponseDtos.add(new ProjectFormtInfoResponseDto(form));
        }

        return projectFormtInfoResponseDtos;
    }

    // 신청서 목록의 복합 기본키를 가져와 해당 신청서 상세조회 (프론트 방식에 따라 불필요할 수 있음)
    public ProjectFormtInfoResponseDto oneProjectForm(Long projectId, Long memberId) throws Exception {
        CompositeMemberProject cmp = new CompositeMemberProject(findMember(memberId), findProject(projectId));

        return projectApplicationFormRepository.oneFormById(cmp)
            .map(ProjectFormtInfoResponseDto::new)
            .orElseThrow(()-> new NullPointerException("존재하지 않는 신청서입니다"));
    }

    // 가입 승인
    @Transactional
    public HttpStatus approval(Long projectId, Long memberId) throws Exception {
        List<Member> members = memberInProject(projectId);
        Project project = findProject(projectId);
        Member member = findMember(memberId);

        for (Member mem: members) {
            if(mem.equals(member)){
                throw new Exception("이미 가입되어있는 회원입니다.");
            }
        }

        ProjectApplicationForm form = projectApplicationFormRepository
            .findById(new CompositeMemberProject(findMember(memberId), findProject(projectId)))
            .orElseThrow(() -> new NullPointerException("존재하지 않는 신청서입니다."));

        addMember(project, member, form.getRole());
        reject(projectId, memberId);

        return HttpStatus.OK;
    }

    // 신청서 제거
    @Transactional
    public HttpStatus reject(Long projectId, Long memberId) throws Exception {
        projectApplicationFormRepository.delete(projectApplicationFormRepository
            .findById(new CompositeMemberProject(findMember(memberId), findProject(projectId)))
            .orElseThrow(() -> new NullPointerException("존재하지 않는 신청서입니다.")));

        return HttpStatus.OK;
    }
}
