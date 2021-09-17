package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.entity.embedded.CompositeProjectTechstack;
import com.ssafy.match.db.repository.MemberProjectRepository;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.ProjectMemberRoleResponseDto;
import com.ssafy.match.group.dto.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.Club;
import com.ssafy.match.group.entity.Project;
import com.ssafy.match.group.entity.ProjectTechstack;
import com.ssafy.match.group.repository.ClubRepository;
import com.ssafy.match.group.repository.ProjectRepository;
import com.ssafy.match.group.repository.ProjectTechstackRepository;
import com.ssafy.match.util.SecurityUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final TechstackRepository techstackRepository;
    private final ProjectTechstackRepository projectTechstackRepository;
    private final DBFileRepository dbFileRepository;
    private final ClubRepository clubRepository;
    private final MemberProjectRepository memberProjectRepository;

    public ResponseEntity<HttpStatus> create(ProjectCreateRequestDto dto) {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.getById(currentMemberId);

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
            .city(dto.getCity())
            .status(Status.모집중)
            .isActive(true)
            .isPublic(dto.isPublic())
            .isParticipate(true)
            .build();

        createTechstack(project, dto.getStack());
        setDBFile(project, dto.getUuid());
        setClub(project, dto.getClubId());
        changeRole(project, dto.getHostRole());

        projectRepository.save(project);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> update(ProjectUpdateRequestDto dto, Long projectId) {

        Project project = projectRepository.getById(projectId); // 존재 하지 않을때 예외처리 필요
        Member member = memberRepository.getById(dto.getHostId());

        project.setName(dto.getName());
        project.setMember(member);
        project.setSchedule(dto.getSchedule());
        project.setBio(dto.getBio());
        project.setPeriod(dto.getPeriod());
        project.setModifyDate(LocalDateTime.now());
        project.setDeveloperMaxCount(dto.getDeveloperMaxCount());
        project.setDesignerMaxCount(dto.getDesignerMaxCount());
        project.setPlannerMaxCount(dto.getPlannerMaxCount());
        project.setCity(dto.getCity());
        project.setStatus(dto.getStatus());
        project.setPublic(dto.isPublic());
        project.setParticipate(dto.isParticipate());

        setDBFile(project, dto.getUuid());
        setClub(project, dto.getClubId());
        changeRole(project, dto.getHostRole());

        projectRepository.save(project);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> delete(Long projectId) {
        Project project = projectRepository.getById(projectId);
        project.setActive(false);

        projectRepository.save(project);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    // 현재 프로젝트 정보 리턴
    public ResponseEntity<ProjectInfoResponseDto> projectInfo(Long projectId) {

        Project project = projectRepository.findById(projectId)
            .orElseThrow(NullPointerException::new);

        ProjectTechstack[] projectTechstacks = projectTechstackRepository.findByProject(project);
        String[] techstack = new String[projectTechstacks.length];

        for (int i = 0; i < projectTechstacks.length; i++) {
            techstack[i] = projectTechstacks[i].getCompositeProjectTechstack().getTechstack().getName();
        }

        ProjectInfoResponseDto responseDto = ProjectInfoResponseDto.builder()
            .name(project.getName())
            .stack(techstack)
            .schedule(project.getSchedule())
            .period(project.getPeriod())
            .hostNickname(project.getMember().getNickname())
            .developerCount(project.getDeveloperCount())
            .developerMaxCount(project.getDeveloperMaxCount())
            .designerCount(project.getDesignerCount())
            .designerMaxCount(project.getDesignerMaxCount())
            .plannerCount(project.getPlannerCount())
            .plannerMaxCount(project.getPlannerMaxCount())
            .isPublic(project.isPublic())
            .city(project.getCity())
            .status(project.getStatus())
            .isParticipate(project.isParticipate())
            .clubName(project.getClub().getName())
            .picData(project.getDbFile().getData())
            .modifyDate(project.getModifyDate())
            .bio(project.getBio())
            .build();

        return ResponseEntity.ok(responseDto);
    }

    // 현재 프로젝트에 어떤 멤버가 속해있는지 멤버 리스트 리턴
    public ResponseEntity<List<Member>> projectMember(Long projectId) {

        projectRepository.findById(projectId)
            .orElseThrow(NullPointerException::new);

        return ResponseEntity.ok(memberProjectRepository.findMemberWithProject(projectId));
    }

    // 해당 역할에 속한 인원 id, name, nickname과 인원 수
    public ResponseEntity<List<ProjectMemberRoleResponseDto>> roleInfo(Long projectId, String role) {

        projectRepository.findById(projectId)
            .orElseThrow(NullPointerException::new);

        List<Member> memberList = memberProjectRepository.findRoleInfo(projectId, role);

        List<ProjectMemberRoleResponseDto> infoList = new ArrayList<>();
        for (Member member : memberList) {
            infoList.add(new ProjectMemberRoleResponseDto(member.getId(), member.getName(),
                member.getNickname()));
        }

        return ResponseEntity.ok(infoList);
    }

    // 첫 생성시 일괄 적용
    public void createTechstack(Project project, String[] techName) {
        for (String tech : techName) {
            Techstack techstack = findTechstack(tech);

            CompositeProjectTechstack compositeProjectTechstack = CompositeProjectTechstack
                .builder()
                .project(project)
                .techstack(techstack)
                .build();

            ProjectTechstack projectTechstack = ProjectTechstack.builder()
                .compositeProjectTechstack(compositeProjectTechstack)
                .build();

            projectTechstackRepository.save(projectTechstack);
        }
    }

    public void addTechstack(Long projectId, String techName) {
        Project project = findProject(projectId);
        Techstack techstack = findTechstack(techName);

        CompositeProjectTechstack compositeProjectTechstack = new CompositeProjectTechstack(
            techstack, project);
        Optional<ProjectTechstack> projectTechstack = projectTechstackRepository
            .findById(compositeProjectTechstack);

        if (projectTechstack.isPresent()) {
            return;
        }
        ProjectTechstack stack = new ProjectTechstack(compositeProjectTechstack);

        projectTechstackRepository.save(stack);
    }

    public void removeTechstack(Long projectId, String techName) {
        Project project = findProject(projectId);
        Techstack techstack = findTechstack(techName);

        CompositeProjectTechstack compositeProjectTechstack = new CompositeProjectTechstack(
            techstack, project);
        Optional<ProjectTechstack> projectTechstack = projectTechstackRepository
            .findById(compositeProjectTechstack);

        if (projectTechstack.isEmpty()) {
            return;
        }
        projectTechstackRepository.delete(projectTechstack.get());
    }

    public Project findProject(Long projectId) {
        return projectRepository.findById(projectId)
            .orElseThrow(() -> new NullPointerException("프로젝트 정보가 없습니다."));
    }

    public Techstack findTechstack(String techName) {
        return techstackRepository.findByName(techName)
            .orElseThrow(() -> new NullPointerException("기술 스택 정보가 없습니다."));
    }

    public void setDBFile(Project project, String uuid) {
        if (uuid == null) {
            project.setDbFile(null);
            return;
        }

        DBFile dbFile = dbFileRepository.findById(uuid)
            .orElseThrow(() -> new NullPointerException("파일 정보가 없습니다."));

        project.setDbFile(dbFile);
    }

    public void setClub(Project project, Long clubId) {
        if (clubId == null) {
            project.setClub(null);
            return;
        }

        Club club = clubRepository.findById(clubId)
            .orElseThrow(() -> new NullPointerException("클럽 정보가 없습니다."));

        project.setClub(club);
    }

    // (아이디어가 생각이 안나서 임시로 If문 사용 조언 구함)
    public void changeRole(Project project, String role) { // 역할 수정했을 때 프로젝트 인원 변화

        Optional<String> now = Optional.ofNullable(project.getHostRole()); // 현재 프로젝트에서의 역할

        if (!now.isPresent()) { // 이제 생성된 프로젝트이거나 이제 가입한 경우
            if (role.equals("디자이너")) {
                project.plusDesigner();
            } else if (role.equals("개발자")) {
                project.plusDeveloper();
            } else if (role.equals("기획자")) {
                project.plusPlanner();
            }
        } else if (now.equals(role)) { // 변경사항이 없는 경우
            return;
        } else {
            if (now.equals("디자이너")) {
                project.minusDesigner();

                if (role.equals("개발자")) {
                    project.plusDeveloper();
                } else if (role.equals("기획자")) {
                    project.plusPlanner();
                }
            } else if (now.equals("개발자")) {
                project.minusDeveloper();

                if (role.equals("기획자")) {
                    project.plusPlanner();
                } else if (role.equals("디자이너")) {
                    project.plusDesigner();
                }
            } else if (now.equals("기획자")) {
                project.minusPlanner();

                if (role.equals("개발자")) {
                    project.plusDeveloper();
                } else if (role.equals("디자이너")) {
                    project.plusDesigner();
                }
            }
        }

        project.setHostRole(role);
    }
}
