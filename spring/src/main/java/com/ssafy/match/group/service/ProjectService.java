package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.group.dto.project.request.ProjectApplicationRequestDto;
import com.ssafy.match.group.dto.project.response.InfoForApplyProjectFormResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectFormtInfoResponseDto;
import com.ssafy.match.group.dto.project.request.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.project.response.ProjectInfoForCreateResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.project.request.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.project.Project;
import java.util.List;
import org.springframework.http.HttpStatus;

public interface ProjectService {

    ProjectInfoForCreateResponseDto getInfoForCreate() throws Exception;

    Long create(ProjectCreateRequestDto dto) throws Exception;

    HttpStatus update(Long projectId, ProjectUpdateRequestDto dto) throws Exception;

    HttpStatus delete(Long projectId) throws Exception;

    ProjectInfoResponseDto projectInfo(Long projectId) throws Exception;

    List<Project> projectInMember(Long memberId) throws Exception;

    void addMember(Project project, Member member, String role) throws Exception;

    void removeMember(Long projectId, Long memberId) throws Exception;

    void changeRole(Project project, Member member, String role) throws Exception;

    InfoForApplyProjectFormResponseDto checkForRegister(Long projectId) throws Exception;

    HttpStatus createForm(Long projectId, ProjectApplicationRequestDto dto) throws Exception;

    List<ProjectFormtInfoResponseDto> allProjectForm(Long projectId) throws Exception;

    List<ProjectFormtInfoResponseDto> allFormByProjectNickname(Long projectId, String nickname) throws Exception;

    ProjectFormtInfoResponseDto oneProjectForm(Long projectId, Long memberId) throws Exception;

    HttpStatus approval(Long projectId, Long memberId) throws Exception;

    HttpStatus reject(Long projectId, Long memberId) throws Exception;
}
