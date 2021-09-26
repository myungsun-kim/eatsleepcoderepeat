package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.group.dto.project.FormRegisterRequestDto;
import com.ssafy.match.group.dto.project.FormtInfoForRegisterResponseDto;
import com.ssafy.match.group.dto.project.FormtInfoResponseDto;
import com.ssafy.match.group.dto.study.Request.StudyCreateRequestDto;
import com.ssafy.match.group.dto.study.Request.StudyUpdateRequestDto;
import com.ssafy.match.group.dto.study.Response.StudyInfoForCreateResponseDto;
import com.ssafy.match.group.dto.study.Response.StudyInfoResponseDto;
import com.ssafy.match.group.entity.study.Study;
import java.util.List;
import org.springframework.http.HttpStatus;

public interface StudyService {

    StudyInfoForCreateResponseDto getInfoForCreate() throws Exception;

    Long create(StudyCreateRequestDto dto) throws Exception;

    HttpStatus update(Long studyId, StudyUpdateRequestDto dto) throws Exception;

    HttpStatus delete(Long studyId) throws Exception;

    StudyInfoResponseDto getInfo(Long studyId) throws Exception;

    void addMember(Study study, Member member) throws Exception;

    void removeMember(Long studyId, Long memberId) throws Exception;

    FormtInfoForRegisterResponseDto checkForRegister(Long projectId) throws Exception;

    HttpStatus createForm(Long projectId, FormRegisterRequestDto dto) throws Exception;

    List<FormtInfoResponseDto> allProjectForm(Long projectId) throws Exception;

    List<FormtInfoResponseDto> allFormByProjectNickname(Long projectId, String nickname) throws Exception;

    FormtInfoResponseDto oneProjectForm(Long projectId, Long memberId) throws Exception;

    HttpStatus approval(Long projectId, Long memberId) throws Exception;

    HttpStatus reject(Long projectId, Long memberId) throws Exception;
}
