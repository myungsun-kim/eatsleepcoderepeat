package com.ssafy.match.group.controller;

import com.ssafy.match.group.dto.project.FormRegisterRequestDto;
import com.ssafy.match.group.dto.project.FormtInfoForRegisterResponseDto;
import com.ssafy.match.group.dto.project.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.project.ProjectInfoForCreateResponseDto;
import com.ssafy.match.group.dto.project.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.project.ProjectUpdateRequestDto;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.entity.project.ProjectApplicationForm;
import com.ssafy.match.group.service.ProjectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/projectform")
public class ProjectFormController {

    private final ProjectService projectService;

    @GetMapping("/forcreate")
    @ApiImplicitParam(name = "dto", value = "신청서 생성을 위한 Dto", required = true, dataType = "FormInfoForRegisterResponseDto", paramType = "json")
    @ApiOperation(value = "신청서 생성을 위한 정보", notes = "<strong>프로젝트를에 가입하기 위한</strong>신청서를 작성하기 위한 정보(전체 기술, 선택할 수 있는 지역 리스트)를 받는다")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<FormtInfoForRegisterResponseDto> checkForRegister(@PathVariable("projectId") Long projectId) throws Exception {
        return ResponseEntity.ok(projectService.checkForRegister(projectId));
    }

    @PostMapping("/{projectId}")
    @ApiImplicitParams({
        @ApiImplicitParam (name = "dto", value = "프로젝트 가입 신청 정보", required = true, dataType = "FormRegisterRequestDto", paramType = "json"),
        @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiOperation(value = "프로젝트 가입 신청", notes = "<strong>받은 신청서 정보로</strong>를 사용해서 프로젝트에 신청을 한다")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<HttpStatus> createForm(@PathVariable("projectId") Long projectId, @RequestBody FormRegisterRequestDto dto) throws Exception {
        return ResponseEntity.ok(projectService.createForm(projectId, dto));
    }

//    @PatchMapping("/{projectId}")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "dto", value = "프로젝트 수정 dto", required = true, dataType = "ProjectUpdateRequestDto", paramType = "json"),
//        @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
//    })
//    @ApiOperation(value = "프로젝트 수정", notes = "<strong>받은 프로젝트 정보</strong>를 사용해서 프로젝트를 수정한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//    })
//    public ResponseEntity<HttpStatus> updateProject(@RequestBody ProjectUpdateRequestDto dto,
//        @PathVariable("projectId") Long projectId) throws Exception {
//        return ResponseEntity.ok(projectService.update(projectId, dto));
//    }
//
//    @DeleteMapping("/{projectId}")
//    @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
//    @ApiOperation(value = "프로젝트 삭제", notes = "<strong>받은 프로젝트 Id</strong>로 프로젝트와 포함된 멤버관계를 삭제한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//    })
//    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") Long projectId)
//        throws Exception {
//        return ResponseEntity.ok(projectService.delete(projectId));
//    }
//
//    @GetMapping("/detail/{projectId}")
//    @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
//    @ApiOperation(value = "프로젝트 상세정보 조회",
//        notes = "<strong>받은 프로젝트 Id</strong>로 해당 프로젝트를 조회 + 전체 기술스택, 역할별 인원 닉네임, 전체 지역 정보, 포함 인원 등")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//    })
//    public ResponseEntity<ProjectInfoResponseDto> projectInfo(@PathVariable("projectId") Long projectId)
//        throws Exception {
//        return ResponseEntity.ok(projectService.projectInfo(projectId));
//    }
//
//    @GetMapping("/info/{memberId}")
//    @ApiImplicitParam(name = "memberId", value = "멤버 Id", required = true, dataType = "Long", paramType = "path")
//    @ApiOperation(value = "특정 멤버가 속한 프로젝트 조회",
//        notes = "<strong>받은 멤버 Id</strong>로 해당 멤버가 속한 프로젝트 정보 조회")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//    })
//    public ResponseEntity<List<Project>> projectInMember(@PathVariable("memberId") Long memberId)
//        throws Exception {
//        return ResponseEntity.ok(projectService.projectInMember(memberId));
//    }

    @GetMapping("/all/{projectId}")
    @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
    @ApiOperation(value = "특정 프로젝트 모든 신청서 조회", notes = "특정 프로젝트의 모든 신청서 리스트를 받는다")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<List<ProjectApplicationForm>> allProjectForm(@PathVariable("projectId") Long projectId) throws Exception {
        return ResponseEntity.ok(projectService.allProjectForm(projectId));
    }

}
