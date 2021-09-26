package com.ssafy.match.group.dto.project;

import com.ssafy.match.db.entity.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectUpdateRequestDto {

    @ApiModelProperty(name = "techstack", example = "['자바', '파이썬', '스프링', '쿠버네티스']")
    @ApiParam(value = "추가된 기술 스택 리스트", required = true)
    private List<String> addStackList;

    @ApiModelProperty(name = "techstack", example = "['자바', '파이썬']")
    @ApiParam(value = "제거된 기술 스택 리스트", required = true)
    private List<String> removeStackList;

    @ApiModelProperty(name = "name", example = "매칭 프로젝트")
    @ApiParam(value = "프로젝트명", required = true)
    private String name;

    @ApiModelProperty(name = "host_name", example = "5")
    @ApiParam(value = "프로젝트장 Id(변경 가능)", required = true)
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

    @ApiModelProperty(name = "developer_count", example = "3")
    @ApiParam(value = "개발자 모집 인원", required = true)
    private int developerMaxCount;

    @ApiModelProperty(name = "designer_count", example = "3")
    @ApiParam(value = "디자이너 모집 인원", required = true)
    private int designerMaxCount;

    @ApiModelProperty(name = "planner_count", example = "3")
    @ApiParam(value = "기획자 모집 인원", required = true)
    private int plannerMaxCount;

    @ApiModelProperty(name = "city", example = "구미")
    @ApiParam(value = "활동지역", required = true)
    private String city;

    @ApiModelProperty(name = "status", example = "모집중, 진행중, 종료됨")
    @ApiParam(value = "프로젝트 상태", required = true)
    private Status status;

    @ApiModelProperty(name = "is_public", example = "false")
    @ApiParam(value = "공개 비공개", required = true)
    private Boolean isPublic;

    @ApiModelProperty(name = "is_participate", example = "false")
    @ApiParam(value = "참여 가능 여부", required = true)
    private Boolean isParticipate;

    @ApiModelProperty(name = "club_id", example = "3")
    @ApiParam(value = "소속된 클럽 id")
    private Long clubId;

    @ApiModelProperty(name = "uuid", example = "3fads23-fdfd13-23d2")
    @ApiParam(value = "사진 고유 uuid")
    private String uuid;

    @ApiModelProperty(name = "host_role", example = "디자이너")
    @ApiParam(value = "프로젝트장 역할", required = true)
    private String hostRole;

    @Builder
    public ProjectUpdateRequestDto(List<String> addStackList,
        List<String> removeStackList, String name, Long hostId, String schedule,
        String bio, int period, int developerMaxCount, int designerMaxCount, int plannerMaxCount,
        String city, Status status, boolean isPublic, boolean isParticipate, Long clubId,
        String uuid, String hostRole) {
        this.addStackList = addStackList;
        this.removeStackList = removeStackList;
        this.name = name;
        this.hostId = hostId;
        this.schedule = schedule;
        this.bio = bio;
        this.period = period;
        this.developerMaxCount = developerMaxCount;
        this.designerMaxCount = designerMaxCount;
        this.plannerMaxCount = plannerMaxCount;
        this.city = city;
        this.status = status;
        this.isPublic = isPublic;
        this.isParticipate = isParticipate;
        this.clubId = clubId;
        this.uuid = uuid;
        this.hostRole = hostRole;
    }
}
