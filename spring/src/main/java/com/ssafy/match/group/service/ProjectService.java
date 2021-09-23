package com.ssafy.match.group.service;

import com.ssafy.match.group.dto.project.FormRegisterRequestDto;
import com.ssafy.match.group.dto.project.FormtInfoForRegisterResponseDto;
import com.ssafy.match.group.dto.project.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.project.ProjectInfoForCreateResponseDto;
import com.ssafy.match.group.dto.project.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.project.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.entity.project.ProjectApplicationForm;
import java.util.List;
import org.springframework.http.HttpStatus;

public interface ProjectService {

    ProjectInfoForCreateResponseDto infoForCreate() throws Exception;

    Long create(ProjectCreateRequestDto dto) throws Exception;

    HttpStatus update(Long projectId, ProjectUpdateRequestDto dto) throws Exception;

    HttpStatus delete(Long projectId) throws Exception;

    ProjectInfoResponseDto projectInfo(Long projectId) throws Exception;

    List<Project> projectInMember(Long memberId) throws Exception;

    void addMember(Long projectId, Long memberId, String role) throws Exception;

    void removeMember(Long projectId, Long memberId) throws Exception;

    void setDBFile(Long projectId, String uuid) throws Exception;

    void setClub(Long projectId, Long clubId) throws Exception;

    void changeRole(Long projectId, Long memberId, String role) throws Exception;

    FormtInfoForRegisterResponseDto checkForRegister(Long projectId) throws Exception;

    HttpStatus createForm(Long projectId, FormRegisterRequestDto dto) throws Exception;

    List<ProjectApplicationForm> allProjectForm(Long projectId) throws Exception;
}
