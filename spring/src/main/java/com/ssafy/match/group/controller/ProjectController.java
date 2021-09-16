package com.ssafy.match.group.controller;

import com.ssafy.match.group.dto.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.ProjectUpdateRequestDto;
import com.ssafy.match.group.service.ProjectService;
import com.ssafy.match.group.service.ProjectServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @ApiImplicitParam(name = "dto", value = "프로젝트 생성 dto", required = true, dataType = "ProjectUpdateRequestDto", paramType = "json")
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
    public ResponseEntity deleteClub(@PathVariable("id") Long projectId) {
        return ResponseEntity.ok(projectService.delete(projectId));
    }

}
