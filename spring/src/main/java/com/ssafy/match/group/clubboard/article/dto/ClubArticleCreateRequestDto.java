package com.ssafy.match.group.clubboard.article.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubArticleCreateRequestDto {

    @ApiModelProperty(name = "boardId", example = "3")
    private int boardId;

    @ApiModelProperty(name = "title", example = "제목")
    private String title;

}
