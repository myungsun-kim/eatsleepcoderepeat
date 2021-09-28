package com.ssafy.match.group.dto.study.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "스터디 생성 정보", description = "스터디명, 기술 스택 리스트, 스케쥴, 기간, 최대인원...을 가진 Request Dto Class")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyCreateRequestDto {

    @ApiModelProperty(name = "name", example = "매칭 스터디")
    @ApiParam(value = "스터디명", required = true)
    private String name;

    @ApiModelProperty(name = "techList", example = "{'자바', '파이썬', '스프링', '쿠버네티스'}")
    @ApiParam(value = "기술 스택 리스트", required = true)
    private List<String> techList;

    @ApiModelProperty(name = "schedule", example = "매주 화, 수 6시")
    @ApiParam(value = "스터디 작업 시간", required = true)
    private String schedule;

    @ApiModelProperty(name = "period", example = "7")
    @ApiParam(value = "스터디 기간(주 단위)", required = true)
    private int period;

    @ApiModelProperty(name = "maxCount", example = "3")
    @ApiParam(value = "스터디 제한 인원", required = true)
    private int maxCount;

    @ApiModelProperty(name = "city", example = "구미")
    @ApiParam(value = "활동지역", required = true)
    private String city;

    @ApiModelProperty(name = "isPublic", example = "false")
    @ApiParam(value = "공개 비공개", required = true)
    private Boolean isPublic;

    @ApiModelProperty(name = "club_id", example = "3")
    @ApiParam(value = "소속된 클럽 id")
    private Long clubId;

    @ApiModelProperty(name = "uuid", example = "3fads23-fdfd13-23d2")
    @ApiParam(value = "사진 고유 uuid")
    private String uuid;

    @ApiModelProperty(name = "bio", example = "알고리즘 스터디입니다.")
    @ApiParam(value = "스터디 소개", required = true)
    private String bio;

}
