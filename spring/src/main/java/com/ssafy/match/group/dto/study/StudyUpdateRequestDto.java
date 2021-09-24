package com.ssafy.match.group.dto.study;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Status;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

@Getter
public class StudyUpdateRequestDto {

    @ApiModelProperty(name = "name", example = "매칭 스터디")
    @ApiParam(value = "스터디명", required = true)
    private String name;

    @ApiModelProperty(name = "host_name", example = "5")
    @ApiParam(value = "스터디장 Id", required = true)
    private Long host_id;

    @ApiModelProperty(name = "schedule", example = "매주 화, 수 6시")
    @ApiParam(value = "스터디 작업 시간", required = true)
    private String schedule;

    @ApiModelProperty(name = "bio", example = "Git 매칭 프로젝트입니다.")
    @ApiParam(value = "스터디 소개", required = true)
    private String bio;

    @ApiModelProperty(name = "period", example = "7")
    @ApiParam(value = "스터디 기간(주 단위)", required = true)
    private int period;

    @ApiModelProperty(name = "developer_count", example = "3")
    @ApiParam(value = "스터디 제한 인원", required = true)
    private int max_count;

    @ApiModelProperty(name = "city", example = "구미")
    @ApiParam(value = "활동지역", required = true)
    private City city;

    @ApiModelProperty(name = "status", example = "모집중, 진행중, 종료됨")
    @ApiParam(value = "스터디 상태", required = true)
    private Status status;

    @ApiModelProperty(name = "is_public", example = "false")
    @ApiParam(value = "공개 비공개", required = true)
    private boolean is_public;

    @ApiModelProperty(name = "is_participate", example = "false")
    @ApiParam(value = "참여 가능 여부", required = true)
    private boolean is_participate;

    @ApiModelProperty(name = "club_id", example = "3")
    @ApiParam(value = "소속된 클럽 id")
    private Long club_id;

    @ApiModelProperty(name = "uuid", example = "3fads23-fdfd13-23d2")
    @ApiParam(value = "사진 고유 uuid")
    private String uuid;

}
