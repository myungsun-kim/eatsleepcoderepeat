package com.ssafy.match.group.dto.study.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InfoForApplyStudyFormResponseDto {

//    private String name;

    private String nickname;

    private String git;

    private String twitter;

    private String facebook;

    private String backjoon;

    private List<String> strong;

    private List<String> knowledgeable;

    private String bio;

}
