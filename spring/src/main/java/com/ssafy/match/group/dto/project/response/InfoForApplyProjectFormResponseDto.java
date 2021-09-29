package com.ssafy.match.group.dto.project.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InfoForApplyProjectFormResponseDto {

    private String name;

    private String position;

//    private String git;
//
//    private String twitter;
//
//    private String facebook;
//
//    private String backjoon;
//
//    private List<Techstack> strong;
//
//    private List<Techstack> knowledgeable;
//
//    private String bio;

    @ApiModelProperty(name = "allTechstack", example = "{'Java', 'Python', 'Spring'}")
    @ApiParam(value = "모든 기술스택 목록", required = true)
    private List<String> allTechstack;

    @ApiModelProperty(name = "projectCity", example = "{'광주', '구미'}")
    @ApiParam(value = "선택할 수 있는 지역 리스트", required = true)
    private List<String> projectCity;
}
