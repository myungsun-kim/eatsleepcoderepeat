package com.ssafy.match.group.controller;

import com.ssafy.match.group.dto.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.ProjectUpdateRequestDto;
import com.ssafy.match.group.service.ProjectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ApiImplicitParam(name = "dto", value = "프로젝트 생성 dto", required = true, dataType = "ProjectCreateRequestDto", paramType = "json")
    @ApiOperation(value = "프로젝트 생성", notes = "<strong>받은 프로젝트 정보</strong>를 사용해서 프로젝트을 생성한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<?> createProject(@RequestBody ProjectCreateRequestDto dto) {
        return ResponseEntity.ok(projectService.create(dto));
    }

    @PatchMapping("/{projectId}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dto", value = "프로젝트 수정 dto", required = true, dataType = "ProjectUpdateRequestDto", paramType = "json"),
        @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiOperation(value = "프로젝트 수정", notes = "<strong>받은 프로젝트 정보</strong>를 사용해서 프로젝트를 수정한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<?> updateProject(@RequestBody ProjectUpdateRequestDto dto,
        @PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(projectService.update(dto, projectId));
    }

    @DeleteMapping("/{projectId}")
    @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
    @ApiOperation(value = "프로젝트 삭제", notes = "<strong>받은 프로젝트 Id</strong>로 프로젝트를 삭제한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity deleteProject(@PathVariable("id") Long projectId) {
        return ResponseEntity.ok(projectService.delete(projectId));
    }

//    @GetMapping("/info/{projectId}")
//    @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
//    @ApiOperation(value = "프로젝트 정보 조회", notes = "<strong>받은 프로젝트 Id</strong>로 해당 프로젝트를 조회한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//    })
//    public ResponseEntity projectInfo(@PathVariable("id") Long projectId) {
//        return ResponseEntity.ok(projectService.projectInfo(projectId));
//    }
//
//    @GetMapping("/member/{projectId}")
//    @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path")
//    @ApiOperation(value = "프로젝트 인원 조회", notes = "<strong>받은 프로젝트 Id</strong>로 프로젝트의 인원 리스트를 조회한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//    })
//    public ResponseEntity projectMember(@PathVariable("id") Long projectId) {
//        return ResponseEntity.ok(projectService.projectMember(projectId));
//    }
//
//    @GetMapping("/{projectId}&{role}")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path"),
//        @ApiImplicitParam(name = "role", value = "찾는 역할", required = true, dataType = "String", paramType = "path")
//    })
//    @ApiOperation(value = "프로젝트 역할 인원 조회", notes = "<strong>받은 프로젝트 Id와 역할</strong>로 해당 역할 인원을 조회한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//    })
//    public ResponseEntity roleInfo(@PathVariable("id") Long projectId, @PathVariable("role") String role) {
//        return ResponseEntity.ok(projectService.roleInfo(projectId, role));
//    }
//
//    @GetMapping("/add/{projectId}&{techName}")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path"),
//        @ApiImplicitParam(name = "techName", value = "기술명", required = true, dataType = "String", paramType = "path")
//    })
//    @ApiOperation(value = "프로젝트 기술 스택 추가", notes = "<strong>받은 프로젝트 Id와 기술 스택 이름</strong>으로 프로젝트에 기술 스택을 추가한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//    })
//    public ResponseEntity addTechstack(@PathVariable("id") Long projectId, @PathVariable("techName") String techName) {
//        projectService.addTechstack(projectId, techName);
//
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
//
//    @GetMapping("/remove/{projectId}&{techName}")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "projectId", value = "프로젝트 ID", required = true, dataType = "Long", paramType = "path"),
//        @ApiImplicitParam(name = "techName", value = "기술명", required = true, dataType = "String", paramType = "path")
//    })
//    @ApiOperation(value = "프로젝트 기술 스택 제거", notes = "<strong>받은 프로젝트 Id와 기술 스택 이름</strong>으로 프로젝트에 기술 스택을 제거한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//    })
//    public ResponseEntity removeTechstack(@PathVariable("id") Long projectId, @PathVariable("techName") String techName) {
//        projectService.removeTechstack(projectId, techName);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }


}
