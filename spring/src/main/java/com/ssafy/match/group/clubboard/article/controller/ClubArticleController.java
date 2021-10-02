package com.ssafy.match.group.clubboard.article.controller;


import com.ssafy.match.group.clubboard.article.dto.ClubArticleCreateRequestDto;
import com.ssafy.match.group.clubboard.article.dto.ClubArticleInfoDto;
import com.ssafy.match.group.clubboard.article.dto.ClubArticleListDto;
import com.ssafy.match.group.clubboard.article.dto.ClubArticleUpdateRequestDto;
import com.ssafy.match.group.clubboard.article.service.ClubArticleService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clubboards")
public class ClubArticleController {
    private final ClubArticleService clubArticleService;

    @GetMapping("/{boardid}/articles")
    @ApiOperation(value = "(클럽)게시물 리스트 조회", notes = "<strong>받은 게시판 id</strong>를 사용해서 게시물들을 조회한다.")
    public ResponseEntity<List<ClubArticleListDto>> getArticles(@PathVariable("boardid") Integer boardid) throws Exception {
        return ResponseEntity.ok(clubArticleService.getClubArticles(boardid));
    }

    @GetMapping("/{boardid}/articles/{articleid}")
    @ApiOperation(value = "(클럽)게시물 상세조회", notes = "<strong>받은 article id</strong>를 사용해서 게시물 상세 조회")
    public ResponseEntity<ClubArticleInfoDto> getArticleDetail(@PathVariable("boardid") Integer boardid, @PathVariable("articleid") Long articleid) throws Exception {
        return ResponseEntity.ok(clubArticleService.getClubArticleDetail(articleid));
    }

    @PostMapping("/{boardid}/articles")
    @ApiOperation(value = "(클럽)게시판의 게시물 생성", notes = "<strong>받은 게시판 id</strong>를 사용해서 게시물을 생성한다.")
    public ResponseEntity<Long> createArticle(@PathVariable("boardid") Integer boardid, @RequestBody ClubArticleCreateRequestDto clubArticleCreateRequestDto) throws Exception {
        return ResponseEntity.ok(clubArticleService.createArticle(boardid, clubArticleCreateRequestDto));
    }

    @PutMapping("/{boardid}/articles/{articleid}")
    @ApiOperation(value = "(클럽)게시판의 게시물 수정", notes = "<strong>받은 게시물 id</strong>를 사용해서 게시물을 수정한다.")
    public ResponseEntity<Long> updateArticle(@PathVariable("boardid") Integer boardid, @PathVariable("articleid") Long articleid, @RequestBody ClubArticleUpdateRequestDto clubArticleUpdateRequestDto) throws Exception {
        return ResponseEntity.ok(clubArticleService.updateArticle(articleid, clubArticleUpdateRequestDto));
    }

    @DeleteMapping("/{boardid}/articles/{articleid}")
    @ApiOperation(value = "(클럽)게시판의 게시물 삭제", notes = "<strong>받은 게시물 id</strong>를 사용해서 게시물을 삭제한다.")
    public ResponseEntity<Boolean> deleteArticle(@PathVariable("boardid") Integer boardid, @PathVariable("articleid") Long articleid) {
        return ResponseEntity.ok(clubArticleService.deleteArticle(articleid));
    }
}