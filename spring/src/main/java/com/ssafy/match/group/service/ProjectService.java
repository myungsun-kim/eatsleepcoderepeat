package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.Club;
import com.ssafy.match.group.entity.Project;
import com.ssafy.match.group.repository.ClubRepository;
import com.ssafy.match.group.repository.ProjectRepository;
import com.ssafy.match.group.repository.ProjectTechstackRepository;
import com.ssafy.match.util.SecurityUtil;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final TechstackRepository techstackRepository;
    private final ProjectTechstackRepository projectTechstackRepository;
    private final DBFileRepository dbFileRepository;
    private final ClubRepository clubRepository;

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

    public void setDBFile(Project project, String uuid) {
        if (uuid == null) {
            project.setDbFile(null);
            return;
        }

        DBFile dbFile = dbFileRepository.getById(uuid);
        project.setDbFile(dbFile);

    }

    public void setClub(Project project, Long clubId) {
        if (clubId == null) {
            project.setClub(null);
            return;
        }

        Club club = clubRepository.getById(clubId);
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
