package com.ssafy.match.group.studyboard.article.controller;


import com.ssafy.match.group.studyboard.article.dto.StudyArticleCreateRequestDto;
import com.ssafy.match.group.studyboard.article.dto.StudyArticleInfoDto;
import com.ssafy.match.group.studyboard.article.dto.StudyArticleListDto;
import com.ssafy.match.group.studyboard.article.service.StudyArticleService;
import com.ssafy.match.group.studyboard.board.dto.StudyBoardInfoDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class StudyArticleController {
    private final StudyArticleService studyArticleService;

    @GetMapping("/articles/{articleid}")
    @ApiOperation(value = "(스터디)게시물 상세조회", notes = "<strong>받은 article id</strong>를 사용해서 게시물 상세 조회")
    public ResponseEntity<StudyArticleInfoDto> getArticles(@PathVariable("articleid") Long articleid) throws Exception {
        return ResponseEntity.ok(studyArticleService.getStudyArticleDetail(articleid));
    }

    @GetMapping("/{boardid}/articles")
    @ApiOperation(value = "(스터디)게시물 리스트 조회", notes = "<strong>받은 게시판 id</strong>를 사용해서 게시물들을 조회한다.")
    public ResponseEntity<List<StudyArticleListDto>> getArticles(@PathVariable("boardid") Integer boardid) throws Exception {
        return ResponseEntity.ok(studyArticleService.getStudyArticles(boardid));
    }

    @PostMapping("/articles")
    @ApiOperation(value = "(스터디)게시판의 게시물 생성", notes = "<strong>받은 게시판 id</strong>를 사용해서 게시물을 생성한다.")
    public ResponseEntity<Long> create(@RequestBody StudyArticleCreateRequestDto studyArticleCreateRequestDto) throws Exception {
        return ResponseEntity.ok(studyArticleService.createArticle(studyArticleCreateRequestDto));
    }




}
