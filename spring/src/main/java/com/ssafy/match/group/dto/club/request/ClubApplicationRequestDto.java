package com.ssafy.match.group.dto.club.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubApplicationRequestDto {

    private String nickname;

    private String city;

    private String git;

    private String twitter;

    private String facebook;

    private String backjoon;

    private String bio;

    private List<String> experiencedTechstack;

    private List<String> beginnerTechstack;

    @ApiModelProperty(name = "uuid", example = "3fads23-fdfd13-23d2")
    @ApiParam(value = "사진 고유 uuid")
    private String uuid;

}