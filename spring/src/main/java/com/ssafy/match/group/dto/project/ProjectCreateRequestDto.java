package com.ssafy.match.group.dto.project;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProjectCreateRequestDto {

    @ApiModelProperty(name = "techList", example = "{'자바', '파이썬', '스프링', '쿠버네티스'}")
    @ApiParam(value = "기술 스택 리스트", required = true)
    private List<String> techList;

    @ApiModelProperty(name = "name", example = "매칭 프로젝트")
    @ApiParam(value = "프로젝트명", required = true)
    private String name;

    @ApiModelProperty(name = "hostId", example = "5")
    @ApiParam(value = "프로젝트장 Id", required = true)
    private Long hostId;

    @ApiModelProperty(name = "schedule", example = "매주 화, 수 6시")
    @ApiParam(value = "프로젝트 작업 시간", required = true)
    private String schedule;

    @ApiModelProperty(name = "bio", example = "Git 매칭 프로젝트입니다.")
    @ApiParam(value = "프로젝트 소개", required = true)
    private String bio;

    @ApiModelProperty(name = "period", example = "7")
    @ApiParam(value = "프로젝트 기간(주 단위)", required = true)
    private int period;

    @ApiModelProperty(name = "developerMaxCount", example = "3")
    @ApiParam(value = "개발자 모집 인원", required = true)
    private int developerMaxCount;

    @ApiModelProperty(name = "designermaxCount", example = "3")
    @ApiParam(value = "디자이너 모집 인원", required = true)
    private int designerMaxCount;

    @ApiModelProperty(name = "plannerMaxCount", example = "3")
    @ApiParam(value = "기획자 모집 인원", required = true)
    private int plannerMaxCount;

    @ApiModelProperty(name = "city", example = "구미")
    @ApiParam(value = "활동지역", required = true)
    private String city;

    @ApiModelProperty(name = "isPublic", example = "false")
    @ApiParam(value = "공개 비공개", required = true)
    private boolean isPublic;

    @ApiModelProperty(name = "clubId", example = "3")
    @ApiParam(value = "소속된 클럽 id")
    private Long clubId;

    @ApiModelProperty(name = "uuid", example = "3fads23-fdfd13-23d2")
    @ApiParam(value = "사진 고유 uuid")
    private String uuid;

    @ApiModelProperty(name = "hostRole", example = "디자이너")
    @ApiParam(value = "프로젝트장 역할", required = true)
    private String hostRole;

    @Builder
    public ProjectCreateRequestDto(List<String> techList, String name, Long hostId,
        String schedule, String bio, int period, int developerMaxCount, int designerMaxCount,
        int plannerMaxCount, String city, boolean isPublic, Long clubId, String uuid,
        String hostRole) {
        this.techList = techList;
        this.name = name;
        this.hostId = hostId;
        this.schedule = schedule;
        this.bio = bio;
        this.period = period;
        this.developerMaxCount = developerMaxCount;
        this.designerMaxCount = designerMaxCount;
        this.plannerMaxCount = plannerMaxCount;
        this.city = city;
        this.isPublic = isPublic;
        this.clubId = clubId;
        this.uuid = uuid;
        this.hostRole = hostRole;
    }
}