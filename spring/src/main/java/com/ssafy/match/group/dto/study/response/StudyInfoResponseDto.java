package com.ssafy.match.group.dto.study.response;

import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.group.dto.MemberDto;
import com.ssafy.match.group.dto.club.ClubDto;
import com.ssafy.match.group.entity.study.Study;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "스터디 수정, 조회 정보", description = "스터디의 상세 정보와 수정(status, city 등) Response Dto Class")
@Getter
@Setter
@Builder
public class StudyInfoResponseDto {

    @ApiModelProperty(name = "name", example = "알고리즘 스터디")
    @ApiParam(value = "스터디명", required = true)
    private String name;

    @ApiModelProperty(name = "schedule", example = "매주 화, 수 6시")
    @ApiParam(value = "작업 시간", required = true)
    private String schedule;

    @ApiModelProperty(name = "period", example = "7")
    @ApiParam(value = "기간(주 단위)", required = true)
    private int period;

    @ApiModelProperty(name = "hostNickname", example = "[id: 3, name: '박범진', nickname: 'BJP']")
    @ApiParam(value = "스터디장 정보(id, name, nickname)", required = true)
    private MemberDto host;

    @ApiModelProperty(name = "memberCount", example = "3")
    @ApiParam(value = "현재 인원", required = true)
    private int memberCount;

    @ApiModelProperty(name = "maxCount", example = "3")
    @ApiParam(value = "최대 인원", required = true)
    private int maxCount;

    @ApiModelProperty(name = "isPublic", example = "false")
    @ApiParam(value = "공개 비공개", required = true)
    private Boolean isPublic;

    @ApiModelProperty(name = "isParticipate", example = "false")
    @ApiParam(value = "참여 가능 여부", required = true)
    private Boolean isParticipate;

    @ApiModelProperty(name = "city", example = "구미")
    @ApiParam(value = "지역", required = true)
    private String city;

    @ApiModelProperty(name = "status", example = "모집중, 진행중, 종료됨")
    @ApiParam(value = "프로젝트 상태", required = true)
    private String status;

    @ApiModelProperty(name = "clubId", example = "[id: 3, name: 'SSAFY']")
    @ApiParam(value = "소속된 클럽 정보")
    private ClubDto club;

    @ApiModelProperty(name = "dbFile")
    @ApiParam(value = "사진 정보")
    private DBFile dbFile;

    @ApiModelProperty(name = "modifyDate", example = "2021-09-06 06:57:37.667537")
    @ApiParam(value = "마지막 수정일")
    private LocalDateTime modifyDate;

    @ApiModelProperty(name = "bio", example = "Git 매칭 프로젝트입니다.")
    @ApiParam(value = "프로젝트 소개", required = true)
    private String bio;

    @ApiModelProperty(name = "allTechstack", example = "{'Java', 'Python', 'Spring'}")
    @ApiParam(value = "모든 기술스택 목록", required = true)
    private List<String> allTechstack;

    @ApiModelProperty(name = "studyTechstack", example = "{'Java', 'Python'}")
    @ApiParam(value = "해당 스터디가 가지고 있는 기술 스택 리스트", required = true)
    private List<String> studyTechstack;

    @ApiModelProperty(name = "hostClub", example = "{{clubId: 1, clubName: '첫번째', ...}, {clubId: 2, clubName: '두번째', ...}}")
    @ApiParam(value = "해당 호스트가 포함되어있는 클럽 목록 (수정시 클럽 수정을 위한)", required = true)
    private List<ClubDto> clubList;

    @ApiModelProperty(name = "projectMember", example = "{[id: 3, name: '박범진', nickname: 'BJP'], [id: 4, name: '김아무개', nickname: '호롤로']}")
    @ApiParam(value = "해당 스터디에 속한 멤버 조회", required = true)
    private List<MemberDto> memberDtos;

    @ApiModelProperty(name = "projectCity", example = "{'광주', '구미'}")
    @ApiParam(value = "선택할 수 있는 지역 리스트", required = true)
    private List<String> cityList;

    @ApiModelProperty(name = "projectCity", example = "{'모집중', '진행중', '마감'}")
    @ApiParam(value = "선택할 수 있는 상태 리스트", required = true)
    private List<String> statusList;

    public StudyInfoResponseDto(Study study) {
        this.name = study.getName();
        this.schedule = study.getSchedule();
        this.period = study.getPeriod();
        this.maxCount = study.getMaxCount();
        this.isPublic = study.getIsPublic();
        this.isParticipate = study.getIsParticipate();
        this.city = study.getCity().name();
        this.status = study.getStatus().name();
        this.dbFile = study.getDbFile();
        this.modifyDate = study.getModifyDate();
        this.bio = study.getBio();
    }
}
