package com.ssafy.match.group.dto.project.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import javax.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectApplicationRequestDto {

    private String nickname;

    private String city;

    private String role;

    private String position;

    private String git;

    private String twitter;

    private String facebook;

    private String backjoon;

    private String bio;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ApiModelProperty(name = "uuid", example = "3fads23-fdfd13-23d2")
    @ApiParam(value = "사진 고유 uuid")
    private String uuid;

}