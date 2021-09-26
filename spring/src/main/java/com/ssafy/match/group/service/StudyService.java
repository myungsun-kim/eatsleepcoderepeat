package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.group.dto.study.request.StudyApplicationRequestDto;
import com.ssafy.match.group.dto.study.request.StudyCreateRequestDto;
import com.ssafy.match.group.dto.study.request.StudyUpdateRequestDto;
import com.ssafy.match.group.dto.study.response.InfoForRegisterStudyFormResponseDto;
import com.ssafy.match.group.dto.study.response.StudyFormInfoResponseDto;
import com.ssafy.match.group.dto.study.response.StudyInfoForCreateResponseDto;
import com.ssafy.match.group.dto.study.response.StudyInfoResponseDto;
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

    InfoForRegisterStudyFormResponseDto getInfoForRegister(Long studyId) throws Exception;

    HttpStatus createForm(Long studyId, StudyApplicationRequestDto dto) throws Exception;

    List<StudyFormInfoResponseDto> allStudyForm(Long studyId) throws Exception;

    List<StudyFormInfoResponseDto> allFormByStudyNickname(Long studyId, String nickname) throws Exception;

    StudyFormInfoResponseDto oneStudyForm(Long studyId, Long memberId) throws Exception;

    HttpStatus approval(Long studyId, Long memberId) throws Exception;

    HttpStatus reject(Long studyId, Long memberId) throws Exception;
}
