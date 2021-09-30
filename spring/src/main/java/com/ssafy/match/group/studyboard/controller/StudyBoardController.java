package com.ssafy.match.group.studyboard.controller;


import com.ssafy.match.group.studyboard.dto.StudyBoardCreateRequestDto;
import com.ssafy.match.group.studyboard.service.StudyBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study/board")
public class StudyBoardController {
    private final StudyBoardService studyBoardService;

    @PostMapping
    @ApiOperation(value = "(스터디)게시판 생성", notes = "<strong>받은 스터디 정보</strong>를 사용해서 게시판을 생성한다.")
    public ResponseEntity<Long> create(@RequestBody StudyBoardCreateRequestDto studyBoardCreateRequestDto) throws Exception {
        return ResponseEntity.ok(studyBoardService.create(studyBoardCreateRequestDto));
    }

}
