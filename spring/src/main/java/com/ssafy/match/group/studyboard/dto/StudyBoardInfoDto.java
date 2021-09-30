package com.ssafy.match.group.studyboard.dto;

import com.ssafy.match.group.studyboard.entity.StudyBoard;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StudyBoardInfoDto {
    @ApiModelProperty(name="id", example = "3")
    private Integer id;

    @ApiModelProperty(name = "name", example = "일정게시판")
    private String name;

    public static StudyBoardInfoDto of(StudyBoard studyBoard) {
        return StudyBoardInfoDto.builder()
                .id(studyBoard.getId())
                .name(studyBoard.getName())
                .build();
    }

    @Builder
    public StudyBoardInfoDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
