package com.ssafy.match.group.controller;

import com.ssafy.match.group.dto.project.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.project.ProjectInfoForCreateResponseDto;
import com.ssafy.match.group.dto.project.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.project.ProjectUpdateRequestDto;
import com.ssafy.match.group.dto.study.Request.StudyCreateRequestDto;
import com.ssafy.match.group.dto.study.Request.StudyUpdateRequestDto;
import com.ssafy.match.group.dto.study.Response.StudyInfoForCreateResponseDto;
import com.ssafy.match.group.dto.study.Response.StudyInfoResponseDto;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.service.StudyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;

    @GetMapping("/infoforcreate")
    @ApiOperation(value = "스터디 생성을 위한 정보", notes = "<strong>스터디를 생성하기 위한</strong> 전체 기술, 생성할 멤버의 클럽, 선택할 수 있는 지역 리스트를 받는다")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<StudyInfoForCreateResponseDto> getInfoForCreate() throws Exception {
        return ResponseEntity.ok(studyService.getInfoForCreate());
    }

    @PostMapping
//    @ApiImplicitParam(name = "dto", value = "프로젝트 생성 dto", required = true, dataType = "ProjectCreateRequestDto", paramType = "json")
    @ApiOperation(value = "스터디 생성", notes = "<strong>받은 스터디 정보</strong>를 사용해서 스터디을 생성한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<Long> create(@RequestBody StudyCreateRequestDto dto) throws Exception {
        return ResponseEntity.ok(studyService.create(dto));
    }

    @PatchMapping("/{studyId}")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "dto", value = "프로젝트 수정 dto", required = true, dataType = "ProjectUpdateRequestDto", paramType = "json"),
//        @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
//    })
    @ApiOperation(value = "스터디 수정", notes = "<strong>받은 스터디 정보</strong>를 사용해서 스터디를 수정한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<HttpStatus> update(@PathVariable("studyId") Long studyId,
        @RequestBody StudyUpdateRequestDto dto) throws Exception {
        return ResponseEntity.ok(studyService.update(studyId, dto));
    }

    @DeleteMapping("/{studyId}")
//    @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
    @ApiOperation(value = "스터디 삭제", notes = "<strong>받은 스터디 Id</strong>로 스터디와 포함된 멤버관계를 삭제한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable("studyId") Long studyId)
        throws Exception {
        return ResponseEntity.ok(studyService.delete(studyId));
    }

    @DeleteMapping("/{studyId}&{memberId}")
//    @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
    @ApiOperation(value = "스터디 탈퇴", notes = "<strong>받은 스터디 id와 멤버 id</strong>로 스터디에서 탈퇴한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public void delete(@PathVariable("studyId") Long studyId,
        @PathVariable("memberId") Long memberId) throws Exception {
        studyService.removeMember(studyId, memberId);
    }

    @GetMapping("/{studyId}")
//    @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
    @ApiOperation(value = "스터디 상세정보 조회",
        notes = "<strong>받은 스터디 id</strong>로 해당 스터디 정보 + 수정을 위한 정보(사용자 클럽 리스트, 지역, 상태 리스트 등")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<StudyInfoResponseDto> getInfo(@PathVariable("studyId") Long studyId)
        throws Exception {
        return ResponseEntity.ok(studyService.getInfo(studyId));
    }

}
