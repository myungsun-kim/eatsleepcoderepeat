package com.ssafy.match.group.dto;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Status;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.Lob;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectInfoResponseDto {

    @ApiModelProperty(name = "name", example = "매칭 프로젝트")
    @ApiParam(value = "프로젝트명", required = true)
    private String name;

    @ApiModelProperty(name = "techstack", example = "['자바', '파이썬', '스프링', '쿠버네티스']")
    @ApiParam(value = "기술 스택", required = true)
    private String[] stack;

    @ApiModelProperty(name = "schedule", example = "매주 화, 수 6시")
    @ApiParam(value = "프로젝트 작업 시간", required = true)
    private String schedule;

    @ApiModelProperty(name = "period", example = "7")
    @ApiParam(value = "프로젝트 기간(주 단위)", required = true)
    private int period;

    @ApiModelProperty(name = "hostNickname", example = "MinMin")
    @ApiParam(value = "프로젝트장 닉네임", required = true)
    private String hostNickname;

    @ApiModelProperty(name = "developerCount", example = "3")
    @ApiParam(value = "개발자 현재 인원", required = true)
    private int developerCount;

    @ApiModelProperty(name = "developerMaxCount", example = "3")
    @ApiParam(value = "개발자 모집 인원", required = true)
    private int developerMaxCount;

    @ApiModelProperty(name = "designerCount", example = "3")
    @ApiParam(value = "디자이너 현재 인원", required = true)
    private int designerCount;

    @ApiModelProperty(name = "designerMaxCount", example = "3")
    @ApiParam(value = "디자이너 모집 인원", required = true)
    private int designerMaxCount;

    @ApiModelProperty(name = "plannerCount", example = "3")
    @ApiParam(value = "기획자 현재 인원", required = true)
    private int plannerCount;

    @ApiModelProperty(name = "plannerMaxCount", example = "3")
    @ApiParam(value = "기획자 모집 인원", required = true)
    private int plannerMaxCount;

    @ApiModelProperty(name = "isPublic", example = "false")
    @ApiParam(value = "공개 비공개", required = true)
    private boolean isPublic;

    @ApiModelProperty(name = "city", example = "구미")
    @ApiParam(value = "활동지역", required = true)
    private City city;

    @ApiModelProperty(name = "status", example = "모집중, 진행중, 종료됨")
    @ApiParam(value = "프로젝트 상태", required = true)
    private Status status;

    @ApiModelProperty(name = "isParticipate", example = "false")
    @ApiParam(value = "참여 가능 여부", required = true)
    private boolean isParticipate;

    @ApiModelProperty(name = "clubName", example = "SSAFY")
    @ApiParam(value = "소속된 클럽명")
    private String clubName;

    @ApiModelProperty(name = "picData")
    @ApiParam(value = "사진 데이터")
    @Lob
    private byte[] picData;

    @ApiModelProperty(name = "modifyDate", example = "2021-09-06 06:57:37.667537")
    @ApiParam(value = "마지막 수정일")
    private LocalDateTime modifyDate;

    @ApiModelProperty(name = "bio", example = "Git 매칭 프로젝트입니다.")
    @ApiParam(value = "프로젝트 소개", required = true)
    private String bio;

}
