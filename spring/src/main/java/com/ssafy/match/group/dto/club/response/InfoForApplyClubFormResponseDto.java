package com.ssafy.match.group.dto.club.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InfoForApplyClubFormResponseDto {

//    private String name;

    @ApiModelProperty(name = "nickname", example = "4")
    @ApiParam(value = "신청 유저의 닉네임", required = true)
    private String nickname;
    @ApiModelProperty(name = "git", example = "github")
    @ApiParam(value = "Git 닉네임", required = true)
    private String git;
    @ApiModelProperty(name = "twitter", example = "twitter.com")
    @ApiParam(value = "Twitter 주소", required = true)
    private String twitter;
    @ApiModelProperty(name = "facebook", example = "facebook.com")
    @ApiParam(value = "Facebook 주소", required = true)
    private String facebook;
    @ApiModelProperty(name = "backjoon", example = "BEOMKING")
    @ApiParam(value = "백준 닉네임", required = true)
    private String backjoon;

    private List<String> experiencedTechstack;

    private List<String> beginnerTechstack;

    private String bio;

}
