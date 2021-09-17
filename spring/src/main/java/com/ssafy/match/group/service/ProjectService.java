package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.group.dto.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.ProjectMemberRoleResponseDto;
import com.ssafy.match.group.dto.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.Project;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<HttpStatus> create(ProjectCreateRequestDto dto);
    ResponseEntity<HttpStatus> update(ProjectUpdateRequestDto dto, Long projectId);
    ResponseEntity<HttpStatus> delete(Long projectId);
    Project findProject(Long projectId);
    Techstack findTechstack(String techName);
    ResponseEntity<ProjectInfoResponseDto> projectInfo(Long projectId);
    ResponseEntity<List<Member>> projectMember(Long projectId);
    ResponseEntity<List<ProjectMemberRoleResponseDto>> roleInfo(Long projectId, String role);
    void createTechstack(Project project, String[] techName);
    void addTechstack(Long projectId, String techName);
    void removeTechstack(Long projectId, String techName);
    void setDBFile(Project project, String uuid);
    void setClub(Project project, Long clubId);
    void changeRole(Project project, String role);
}
