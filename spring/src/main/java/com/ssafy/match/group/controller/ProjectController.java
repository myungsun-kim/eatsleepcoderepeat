package com.ssafy.match.group.controller;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.Project;
import com.ssafy.match.group.repository.ClubRepository;
import com.ssafy.match.group.repository.ProjectRepository;
import com.ssafy.match.group.repository.ProjectTechstackRepository;
import com.ssafy.match.group.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TechstackRepository techstackRepository;
    @Autowired
    ProjectTechstackRepository projectTechstackRepository;
    @Autowired
    DBFileRepository dbFileRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    ProjectService projectService;

    @PostMapping
    @ApiOperation(value = "프로젝트 생성", notes = "<strong>받은 프로젝트 정보</strong>를 사용해서 프로젝트을 생성한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<?> createProject(@RequestBody ProjectCreateRequestDto dto) {

        Member member = memberRepository.getById(dto.getHost_id());

        Project project = new Project(dto.getName(), member, "",
            LocalDateTime.now(),
            LocalDateTime.now(), dto.getSchedule(), dto.getPeriod(), dto.getBio(),
            0, dto.getDeveloper_count(), 0, dto.getPlanner_count(),
            0, dto.getDesigner_count(), dto.getCity(), Status.모집중,
            true, dto.is_public(), true, null, null);

        if (dto.getUuid() != null) {
            project.setDbFile(dbFileRepository.getById(dto.getUuid()));
        }
        if (dto.getClub_id() != null) {
            project.setClub(clubRepository.getById(dto.getClub_id()));
        }

        projectService.changeRole(project, dto.getHost_role());

        projectRepository.save(project);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "프로젝트트 수정", notes = "<strong>받은 프로젝트트 정보</strong>를 사용해서 프로젝트를 수정한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<?> updateProject(@RequestBody ProjectUpdateRequestDto dto,
        @PathVariable("id") Long id) {

        Project project = projectRepository.getById(id);
        Member member = memberRepository.getById(dto.getHost_id());

        project.setName(dto.getName());
        project.setMember(member);
        project.setSchedule(dto.getSchedule());
        project.setBio(dto.getBio());
        project.setPeriod(dto.getPeriod());
        project.setModify_date(LocalDateTime.now());
        project.setDeveloper_max_count(dto.getDeveloper_count());
        project.setDesigner_max_count(dto.getDesigner_count());
        project.setPlanner_max_count(dto.getPlanner_count());
        project.setCity(dto.getCity());
        project.setStatus(dto.getStatus());
        project.set_public(dto.is_public());
        project.set_participate(dto.is_participate());

        if (dto.getUuid() != null) {
            project.setDbFile(dbFileRepository.getById(dto.getUuid()));
        } else {
            project.setDbFile(null);
        }

        if (dto.getClub_id() != null) {
            project.setClub(clubRepository.getById(dto.getClub_id()));
        } else {
            project.setClub(null);
        }

        projectService.changeRole(project, dto.getHost_role());

        projectRepository.save(project);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "프로젝트 삭제", notes = "<strong>받은 프로젝트 Id</strong>로 프로젝트를 삭제한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity deleteClub(@PathVariable("id") Long id) {

        Project project = projectRepository.getById(id);
        project.set_active(false);

        projectRepository.save(project);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
