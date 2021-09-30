package com.ssafy.match.group.studyboard.article.dto;


import com.ssafy.match.group.studyboard.article.entity.StudyArticle;
import com.ssafy.match.group.studyboard.board.entity.StudyBoard;
import com.ssafy.match.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyArticleCreateRequestDto {

    @ApiModelProperty(name = "title", example = "게시물 제목")
    @ApiParam(value = "게시판 제목", required = true)
    private String title;

    public StudyArticle toStudyBoard(Member member, StudyBoard studyBoard) {
        return StudyArticle.builder()
                .title(title)
                .studyBoard(studyBoard)
                .viewCount(0)
                .member(member)
                .createDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
                .build();
    }
}
