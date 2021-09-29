package com.ssafy.match.group.dto.club.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InfoForApplyClubFormResponseDto {

//    private String name;

    private String nickname;

    private String git;

    private String twitter;

    private String facebook;

    private String backjoon;

    private List<String> experiencedTechstack;

    private List<String> beginnerTechstack;

    private String bio;

}
