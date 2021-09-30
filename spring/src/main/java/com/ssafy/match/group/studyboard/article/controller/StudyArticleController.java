package com.ssafy.match.group.studyboard.article.controller;


import com.ssafy.match.group.studyboard.article.dto.StudyArticleCreateRequestDto;
import com.ssafy.match.group.studyboard.article.service.StudyArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class StudyArticleController {
    private final StudyArticleService studyArticleService;

    @PostMapping("/article")
    @ApiOperation(value = "(스터디)게시판의 게시물 생성", notes = "<strong>받은 게시판 id</strong>를 사용해서 게시물을 생성한다.")
    public ResponseEntity<Long> create(@RequestBody StudyArticleCreateRequestDto studyArticleCreateRequestDto) throws Exception {
        return ResponseEntity.ok(studyArticleService.createArticle(studyArticleCreateRequestDto));
    }
}
