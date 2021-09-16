package com.ssafy.match.group.service;

import com.ssafy.match.group.dto.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<HttpStatus> create(ProjectCreateRequestDto dto);
    ResponseEntity<HttpStatus> update(ProjectUpdateRequestDto dto, Long projectId);
    ResponseEntity<HttpStatus> delete(Long projectId);
    ResponseEntity<ProjectInfoResponseDto> projectInfo(Long projectId);
    ResponseEntity<HttpStatus> projectMember(Long projectId);

    void setDBFile(Project project, String uuid);
    void setClub(Project project, Long clubId);
    void createTechStack(Project project);
    void changeRole(Project project, String role);
}
