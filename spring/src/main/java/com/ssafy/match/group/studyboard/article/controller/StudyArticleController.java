package com.ssafy.match.group.studyboard.article.controller;


import com.ssafy.match.group.studyboard.article.dto.StudyArticleCreateRequestDto;
import com.ssafy.match.group.studyboard.article.dto.StudyArticleInfoDto;
import com.ssafy.match.group.studyboard.article.dto.StudyArticleListDto;
import com.ssafy.match.group.studyboard.article.dto.StudyArticleUpdateRequestDto;
import com.ssafy.match.group.studyboard.article.service.StudyArticleService;
import com.ssafy.match.group.studyboard.board.dto.StudyBoardInfoDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class StudyArticleController {
    private final StudyArticleService studyArticleService;

    @GetMapping("/{boardid}/articles")
    @ApiOperation(value = "(스터디)게시물 리스트 조회", notes = "<strong>받은 게시판 id</strong>를 사용해서 게시물들을 조회한다.")
    public ResponseEntity<List<StudyArticleListDto>> getArticles(@PathVariable("boardid") Integer boardid) throws Exception {
        return ResponseEntity.ok(studyArticleService.getStudyArticles(boardid));
    }

    @GetMapping("/{boardid}/articles/{articleid}")
    @ApiOperation(value = "(스터디)게시물 상세조회", notes = "<strong>받은 article id</strong>를 사용해서 게시물 상세 조회")
    public ResponseEntity<StudyArticleInfoDto> getArticleDetail(@PathVariable("boardid") Integer boardid, @PathVariable("articleid") Long articleid) throws Exception {
        return ResponseEntity.ok(studyArticleService.getStudyArticleDetail(articleid));
    }

    @PostMapping("/{boardid}/articles")
    @ApiOperation(value = "(스터디)게시판의 게시물 생성", notes = "<strong>받은 게시판 id</strong>를 사용해서 게시물을 생성한다.")
    public ResponseEntity<Long> createArticle(@PathVariable("boardid") Integer boardid, @RequestBody StudyArticleCreateRequestDto studyArticleCreateRequestDto) throws Exception {
        return ResponseEntity.ok(studyArticleService.createArticle(boardid, studyArticleCreateRequestDto));
    }

    @PutMapping("/{boardid}/articles/{articleid}")
    @ApiOperation(value = "(스터디)게시판의 게시물 수정", notes = "<strong>받은 게시물 id</strong>를 사용해서 게시물을 수정한다.")
    public ResponseEntity<Long> updateArticle(@PathVariable("boardid") Integer boardid, @PathVariable("articleid") Long articleid, @RequestBody StudyArticleUpdateRequestDto studyArticleUpdateRequestDto) throws Exception {
        return ResponseEntity.ok(studyArticleService.updateArticle(articleid, studyArticleUpdateRequestDto));
    }

    @DeleteMapping("/{boardid}/articles/{articleid}")
    @ApiOperation(value = "(스터디)게시판의 게시물 삭제", notes = "<strong>받은 게시물 id</strong>를 사용해서 게시물을 삭제한다.")
    public ResponseEntity<Boolean> deleteArticle(@PathVariable("boardid") Integer boardid, @PathVariable("articleid") Long articleid) {
        return ResponseEntity.ok(studyArticleService.deleteArticle(articleid));
    }
}
