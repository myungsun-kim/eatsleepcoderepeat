package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.group.dto.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<Long> create(ProjectCreateRequestDto dto);
    ResponseEntity<HttpStatus> update(ProjectUpdateRequestDto dto, Long projectId);
    ResponseEntity<HttpStatus> delete(Long projectId);
//    Project findProject(Long projectId);
//    Techstack findTechstack(String techName);
//    ResponseEntity<ProjectInfoResponseDto> projectInfo(Long projectId);
//    ResponseEntity<List<Member>> projectMember(Long projectId);
//    ResponseEntity<List<ProjectMemberRoleResponseDto>> roleInfo(Long projectId, String role);
//    void createTechstack(Project project, String[] techName);
//    void addTechstack(Long projectId, String techName);
//    void removeTechstack(Long projectId, String techName);
    void setDBFile(Long projectId, String uuid);
    void setClub(Long projectId, Long clubId);
    void changeRole(Long projectId, Long memberId, String role);
}
