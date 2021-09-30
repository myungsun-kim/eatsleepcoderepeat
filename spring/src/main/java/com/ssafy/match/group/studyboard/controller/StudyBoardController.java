package com.ssafy.match.group.studyboard.controller;


import com.ssafy.match.group.studyboard.dto.StudyBoardCreateRequestDto;
import com.ssafy.match.group.studyboard.dto.StudyBoardInfoDto;
import com.ssafy.match.group.studyboard.service.StudyBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study/board")
public class StudyBoardController {
    private final StudyBoardService studyBoardService;

    @GetMapping("{studyid}")
    @ApiOperation(value = "(스터디)게시판 리스트 조회", notes = "<strong>받은 스터디 id</strong>를 사용해서 게시판들을 조회한다.")
    public ResponseEntity<List<StudyBoardInfoDto>> getBoards(@PathVariable("studyid") Long studyid) throws Exception {
        return ResponseEntity.ok(studyBoardService.getStudyBoards(studyid));
    }

    @PostMapping
    @ApiOperation(value = "(스터디)게시판 생성", notes = "<strong>받은 스터디 id</strong>를 사용해서 게시판을 생성한다.")
    public ResponseEntity<Integer> create(@RequestBody StudyBoardCreateRequestDto studyBoardCreateRequestDto) throws Exception {
        return ResponseEntity.ok(studyBoardService.createBoard(studyBoardCreateRequestDto));
    }

    @DeleteMapping("/{boardid}")
    @ApiOperation(value = "(스터디)게시판 삭제", notes = "<strong>받은 스터디 id, 게시판 id</strong>를 사용해서 게시판을 삭제한다.")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable("boardid") Integer boardid) throws Exception {
        return ResponseEntity.ok(studyBoardService.deleteBoard(boardid));
    }

}
