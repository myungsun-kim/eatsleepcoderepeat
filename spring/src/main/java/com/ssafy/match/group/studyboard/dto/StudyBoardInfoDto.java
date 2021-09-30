package com.ssafy.match.group.studyboard.dto;

import com.ssafy.match.group.studyboard.entity.StudyBoard;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StudyBoardInfoDto {
    @ApiModelProperty(name = "name", example = "문일민")
    private String name;

    public static StudyBoardInfoDto of(StudyBoard studyBoard) {
        return StudyBoardInfoDto.builder()
                .name(studyBoard.getName())
                .build();
    }

    @Builder
    public StudyBoardInfoDto(String name) {
        this.name = name;
    }

}
