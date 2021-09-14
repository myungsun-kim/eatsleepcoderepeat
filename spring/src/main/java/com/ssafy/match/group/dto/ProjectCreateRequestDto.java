package com.ssafy.match.group.dto;

import com.ssafy.match.db.entity.City;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

@Getter
public class ProjectCreateRequestDto {

    @ApiModelProperty(name = "name", example = "매칭 프로젝트")
    @ApiParam(value = "프로젝트명", required = true)
    private String name;

    @ApiModelProperty(name = "host_name", example = "5")
    @ApiParam(value = "프로젝트장 Id", required = true)
    private Long host_id;

    @ApiModelProperty(name = "schedule", example = "매주 화, 수 6시")
    @ApiParam(value = "프로젝트 작업 시간", required = true)
    private String schedule;

    @ApiModelProperty(name = "bio", example = "Git 매칭 프로젝트입니다.")
    @ApiParam(value = "프로젝트 소개", required = true)
    private String bio;

    @ApiModelProperty(name = "period", example = "7")
    @ApiParam(value = "프로젝트 기간(주 단위)", required = true)
    private int period;

    @ApiModelProperty(name = "developer_count", example = "3")
    @ApiParam(value = "개발자 모집 인원", required = true)
    private int developer_count;

    @ApiModelProperty(name = "designer_count", example = "3")
    @ApiParam(value = "디자이너 모집 인원", required = true)
    private int designer_count;

    @ApiModelProperty(name = "planner_count", example = "3")
    @ApiParam(value = "기획자 모집 인원", required = true)
    private int planner_count;

    @ApiModelProperty(name = "city", example = "구미")
    @ApiParam(value = "활동지역", required = true)
    private City city;

    @ApiModelProperty(name = "is_public", example = "false")
    @ApiParam(value = "공개 비공개", required = true)
    private boolean is_public;

    @ApiModelProperty(name = "club_id", example = "3")
    @ApiParam(value = "소속된 클럽 id")
    private Long club_id;

    @ApiModelProperty(name = "uuid", example = "3fads23-fdfd13-23d2")
    @ApiParam(value = "사진 고유 uuid")
    private String uuid;

    @ApiModelProperty(name = "host_role", example = "디자이너")
    @ApiParam(value = "프로젝트장 역할", required = true)
    private String host_role;

}
