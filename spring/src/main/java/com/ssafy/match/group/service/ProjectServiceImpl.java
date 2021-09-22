package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.entity.embedded.CompositeMemberProject;
import com.ssafy.match.db.entity.embedded.CompositeProjectTechstack;
import com.ssafy.match.db.repository.MemberClubRepository;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.project.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.project.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.project.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.Club;
import com.ssafy.match.group.entity.MemberProject;
import com.ssafy.match.group.entity.Project;
import com.ssafy.match.group.entity.ProjectTechstack;
import com.ssafy.match.group.repository.ClubRepository;
import com.ssafy.match.group.repository.MemberProjectRepository;
import com.ssafy.match.group.repository.ProjectRepository;
import com.ssafy.match.group.repository.ProjectTechstackRepository;
import com.ssafy.match.util.SecurityUtil;
import java.time.LocalDateTime;
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
        addMember(project.getId(), currentMemberId, dto.getHostRole());

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
        setDBFile(projectId, dto.getUuid());
        setClub(projectId, dto.getClubId());
        addTechstack(projectId, dto.getAddStackList());
        removeTechstack(projectId, dto.getRemoveStackList());
        changeRole(projectId, currentMemberId, dto.getHostRole());

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
            .projectMember(projectMember)
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
    public void addMember(Long projectId, Long memberId, String role) throws Exception {
        Project project = findProject(projectId);
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

        changeRole(projectId, memberId, role);
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
    public void changeRole(Long projectId, Long memberId, String role) throws Exception {
        Project project = findProject(projectId);
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

//        projectRepository.save(project);
    }
}
