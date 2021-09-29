package com.ssafy.match.group.dto.project.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InfoForApplyProjectFormResponseDto {

    private String nickname;

    private String position;

    private String git;

    private String twitter;

    private String facebook;

    private String backjoon;

    private List<String> strong;

    private List<String> knowledgeable;

    private String bio;

}
