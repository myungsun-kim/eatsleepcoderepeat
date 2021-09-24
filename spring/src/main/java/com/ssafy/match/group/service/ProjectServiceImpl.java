package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.group.dto.project.FormRegisterRequestDto;
import com.ssafy.match.group.dto.project.FormtInfoForRegisterResponseDto;
import com.ssafy.match.group.dto.project.ProjectMemberRoleResponseDto;
import com.ssafy.match.group.entity.project.CompositeMemberProject;
import com.ssafy.match.group.entity.project.CompositeProjectTechstack;
import com.ssafy.match.db.repository.MemberClubRepository;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.project.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.project.ProjectInfoForCreateResponseDto;
import com.ssafy.match.group.dto.project.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.project.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.project.MemberProject;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.entity.project.ProjectApplicationForm;
import com.ssafy.match.group.entity.project.ProjectTechstack;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final TechstackRepository techstackRepository;
    private final ProjectTechstackRepository projectTechstackRepository;
    private final DBFileRepository dbFileRepository;
    private final ClubRepository clubRepository;
    private final MemberProjectRepository memberProjectRepository;
    private final MemberClubRepository memberClubRepository;
    private final ProjectApplicationFormRepository projectApplicationFormRepository;

    public ProjectInfoForCreateResponseDto infoForCreate() throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());

        List<String> allTechstack = allTechstackName();
        List<Club> hostClub = memberClubRepository.findClubByMember(member);
        List<String> projectCity = Stream.of(City.values())
            .map(Enum::name)
            .collect(Collectors.toList());

        return ProjectInfoForCreateResponseDto.builder()
            .allTechstack(allTechstack)
            .hostClub(hostClub)
            .projectCity(projectCity)
            .build();
    }
    @Transactional
    public Long create(ProjectCreateRequestDto dto) throws Exception {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        Member member = findMember(currentMemberId);

        Project project = Project.builder()
            .name(dto.getName())
            .member(member)
            .createDate(LocalDateTime.now())
            .modifyDate(LocalDateTime.now())
            .schedule(dto.getSchedule())
            .period(dto.getPeriod())
            .bio(dto.getBio())
            .developerCount(0)
            .developerMaxCount(dto.getDeveloperMaxCount())
            .plannerCount(0)
            .plannerMaxCount(dto.getPlannerMaxCount())
            .designerCount(0)
            .designerMaxCount(dto.getDesignerMaxCount())
            .city(City.from(dto.getCity()))
            .status(Status.모집중)
            .isActive(true)
            .isPublic(dto.isPublic())
            .isParticipate(true)
            .build();

        projectRepository.save(project);

        setDBFile(project.getId(), dto.getUuid());
        setClub(project.getId(), dto.getClubId());
        createTechstack(project.getId());
        addTechstack(project.getId(), dto.getTechList());
        addMember(project, currentMemberId, dto.getHostRole());

        return project.getId();
    }

    @Transactional
    public HttpStatus update(Long projectId, ProjectUpdateRequestDto dto) throws Exception {
        Project project = findProject(projectId);
        Long currentMemberId = SecurityUtil.getCurrentMemberId();

        if (project.getMember().getId() != currentMemberId) {
            throw new Exception("권한이 없습니다.");
        }
//        Member changeMember = findMember(dto.getHostId());

        project.setName(dto.getName());
//        project.setMember(changeMember);
        project.setSchedule(dto.getSchedule());
        project.setBio(dto.getBio());
        project.setPeriod(dto.getPeriod());
        project.setModifyDate(LocalDateTime.now());
        project.setDeveloperMaxCount(dto.getDeveloperMaxCount());
        project.setDesignerMaxCount(dto.getDesignerMaxCount());
        project.setPlannerMaxCount(dto.getPlannerMaxCount());
        project.setCity(City.from(dto.getCity()));
        project.setStatus(dto.getStatus());
        project.setPublic(dto.isPublic());
        project.setParticipate(dto.isParticipate());
        changeRole(project, currentMemberId, dto.getHostRole());
        setDBFile(projectId, dto.getUuid());
        setClub(projectId, dto.getClubId());
        addTechstack(projectId, dto.getAddStackList());
        removeTechstack(projectId, dto.getRemoveStackList());


        projectRepository.save(project);

        return HttpStatus.OK;
    }

    @Transactional
    public HttpStatus delete(Long projectId) throws Exception {
        Project project = findProject(projectId);
        Long currentMemberId = SecurityUtil.getCurrentMemberId();

        if (project.getMember().getId() != currentMemberId) {
            throw new Exception("권한이 없습니다.");
        }

        List<MemberProject> memberProjects = memberProjectRepository.findMemberWithProject(project);
        for (MemberProject mem : memberProjects) {
            mem.deactivation();
        }

        project.setActive(false);
        projectRepository.save(project);

        return HttpStatus.OK;
    }

    // 현재 프로젝트 정보 리턴
    public ProjectInfoResponseDto projectInfo(Long projectId) throws Exception {

        Project project = findProject(projectId);
        if (SecurityUtil.getCurrentMemberId() != project.getMember().getId()
            && project.isPublic() == false) {
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
            .isPublic(project.isPublic())
            .city(project.getCity())
            .status(project.getStatus())
            .isParticipate(project.isParticipate())
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

    // 첫 생성시 일괄 적용
    @Transactional
    public void createTechstack(Long projectId) throws Exception {
        Project project = findProject(projectId);
        List<Techstack> techstacks = techstackRepository.findAll();

        for (Techstack tech : techstacks) {
            CompositeProjectTechstack compositeProjectTechstack = CompositeProjectTechstack
                .builder()
                .project(project)
                .techstack(tech)
                .build();

            ProjectTechstack projectTechstack = ProjectTechstack.builder()
                .compositeProjectTechstack(compositeProjectTechstack)
                .isActive(false)
                .build();

            projectTechstackRepository.save(projectTechstack);
        }
    }

    @Transactional
    public void addTechstack(Long projectId, List<String> techName) throws Exception {
        Project project = findProject(projectId);

        for (String name : techName) {
            Techstack techstack = findTechstack(name);

            CompositeProjectTechstack compositeProjectTechstack = new CompositeProjectTechstack(
                techstack, project);
            // DB에 해당 프로젝트 기술스택이 초기화 되어있지 않으면 새로 생성
//            ProjectTechstack projectTechstack = projectTechstackRepository
//                .findById(compositeProjectTechstack).orElseGet(() -> new ProjectTechstack(compositeProjectTechstack, true));
            Optional<ProjectTechstack> projectTechstack = projectTechstackRepository
                .findById(compositeProjectTechstack);

            if (projectTechstack.isPresent()) {
                projectTechstack.get().activation();
            } else {
                ProjectTechstack newProjectTechstack = new ProjectTechstack(
                    compositeProjectTechstack, true);
                projectTechstackRepository.save(newProjectTechstack);
            }

        }

    }

    @Transactional
    public void removeTechstack(Long projectId, List<String> techName) throws Exception {
        Project project = findProject(projectId);

        for (String name : techName) {
            Techstack techstack = findTechstack(name);

            CompositeProjectTechstack compositeProjectTechstack = new CompositeProjectTechstack(
                techstack, project);
            // DB에 해당 프로젝트 기술스택이 초기화 되어있지 않으면 새로 생성
            ProjectTechstack projectTechstack = projectTechstackRepository
                .findById(compositeProjectTechstack)
                .orElseThrow(() -> new NullPointerException("해당 기술 스택이 초기화되지 않았습니다."));

            projectTechstack.deactivation();
        }
    }

    @Transactional
    public void addMember(Project project, Long memberId, String role) throws Exception {
//        Project project = findProject(projectId);
        Member member = findMember(memberId);

        CompositeMemberProject compositeMemberProject = new CompositeMemberProject(member, project);
        // DB에 해당 멤버 기록이 없다면 새로 생성
        MemberProject memberProject = memberProjectRepository
            .findById(compositeMemberProject)
            .orElseGet(() -> MemberProject.builder()
                .compositeMemberProject(compositeMemberProject)
                .build());

        memberProject.setRegisterDate(LocalDateTime.now());
        memberProject.activation();
        memberProjectRepository.save(memberProject);

        changeRole(project, memberId, role);
    }

    @Transactional
    public void removeMember(Long projectId, Long memberId) throws Exception {
        Project project = findProject(projectId);
        Member member = findMember(memberId);

        if(project.getMember().getId() == memberId){
            throw new Exception("프로젝트장은 탈퇴할 수 없습니다.");
        }

        CompositeMemberProject compositeMemberProject = new CompositeMemberProject(member, project);
        // DB에 해당 멤버 기록이 없다면 새로 생성
        MemberProject memberProject = memberProjectRepository.findById(compositeMemberProject)
            .orElseThrow(() -> new NullPointerException("이미 탈퇴된 멤버입니다."));

        memberProject.setRegisterDate(LocalDateTime.now());
        memberProject.deactivation();
        changeRole(project, memberId, "");
//        memberProjectRepository.save(memberProject);
    }

    public Project findProject(Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new NullPointerException("프로젝트 정보가 없습니다."));

        if (project.isActive() == false) {
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

    @Transactional
    public void setDBFile(Long projectId, String uuid) throws Exception {
        Project project = findProject(projectId);

        if (uuid == null) {
            project.setDbFile(null);
            return;
        }

        DBFile dbFile = dbFileRepository.findById(uuid)
            .orElseThrow(() -> new NullPointerException("파일 정보가 없습니다."));

        project.setDbFile(dbFile);
    }

    @Transactional
    public void setClub(Long projectId, Long clubId) throws Exception {
        Project project = findProject(projectId);

        if (clubId == null) {
            project.setClub(null);
            return;
        }

        Club club = clubRepository.findById(clubId)
            .orElseThrow(() -> new NullPointerException("클럽 정보가 없습니다."));

        project.setClub(club);
    }

    // (아이디어가 생각이 안나서 임시로 If문 사용 조언 구함)
    @Transactional
    public void changeRole(Project project, Long memberId, String role) throws Exception {
//        Project project = findProject(projectId);
        Member member = findMember(memberId);
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
    public FormtInfoForRegisterResponseDto checkForRegister(Long projectId) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Project project = findProject(projectId);

        List<Member> memberList = memberInProject(projectId);
        for (Member mem: memberList) {
            if(SecurityUtil.getCurrentMemberId() == mem.getId()){
                throw new Exception("이미 가입한 멤버입니다.");
            }
        }

        if(!project.isParticipate()){
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

    // 특정 프로젝트 모든 신청서 조회
    public List<ProjectApplicationForm> allProjectForm(Long projectId) throws Exception {
        Project project = findProject(projectId);

        if(SecurityUtil.getCurrentMemberId() != project.getMember().getId()){
            throw new Exception("조회 권한이 없습니다.");
        }

        return projectApplicationFormRepository.formByProjectId(project);
    }

    // 신청서 목록의 복합 기본키를 가져와 해당 신청서 상세조회
    public ProjectApplicationForm oneProjectForm(CompositeMemberProject cmp){
        return projectApplicationFormRepository.oneFormById(cmp)
            .orElseThrow(()-> new NullPointerException("존재하지 않는 신청서입니다"));
    }
}
