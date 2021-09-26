package com.ssafy.match.group.dto.project.response;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.group.entity.club.Club;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectInfoResponseDto {

    @ApiModelProperty(name = "name", example = "매칭 프로젝트")
    @ApiParam(value = "프로젝트명", required = true)
    private String name;

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

    @ApiModelProperty(name = "developerNicknames", example = "{'Minsu', 'Sun'}")
    @ApiParam(value = "해당 프로젝트에 속해있는 개발자의 닉네임", required = true)
    private List<String> developerNicknames;

    @ApiModelProperty(name = "developerMaxCount", example = "3")
    @ApiParam(value = "개발자 모집 인원", required = true)
    private int developerMaxCount;

    @ApiModelProperty(name = "designerCount", example = "3")
    @ApiParam(value = "디자이너 현재 인원", required = true)
    private int designerCount;

    @ApiModelProperty(name = "designerNicknames", example = "{'Minsu', 'Sun'}")
    @ApiParam(value = "해당 프로젝트에 속해있는 디자이너의 닉네임", required = true)
    private List<String> designerNicknames;

    @ApiModelProperty(name = "designerMaxCount", example = "3")
    @ApiParam(value = "디자이너 모집 인원", required = true)
    private int designerMaxCount;

    @ApiModelProperty(name = "plannerCount", example = "3")
    @ApiParam(value = "기획자 현재 인원", required = true)
    private int plannerCount;

    @ApiModelProperty(name = "plannerNicknames", example = "{'Minsu', 'Sun'}")
    @ApiParam(value = "해당 프로젝트에 속해있는 기획자의 닉네임", required = true)
    private List<String> plannerNicknames;

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

    @ApiModelProperty(name = "clubId", example = "1")
    @ApiParam(value = "소속된 클럽 Id")
    private Long clubId;

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

    @ApiModelProperty(name = "allTechstack", example = "{'Java', 'Python', 'Spring'}")
    @ApiParam(value = "모든 기술스택 목록", required = true)
    private List<String> allTechstack;

    @ApiModelProperty(name = "projectTechstack", example = "{'Java', 'Python'}")
    @ApiParam(value = "해당 프로젝트가 가지고 있는 기술 스택 목록", required = true)
    private List<String> projectTechstack;

    @ApiModelProperty(name = "hostClub", example = "{{clubId: 1, clubName: '첫번째', ...}, {clubId: 2, clubName: '두번째', ...}}")
    @ApiParam(value = "해당 프로젝트 호스트가 포함되어있는 클럽 목록 (수정시 클럽 수정을 위한)", required = true)
    private List<Club> hostClub;

    @ApiModelProperty(name = "projectMember", example = "{{memberId: 1, memberName: '첫번째', ...}, {memberId: 2, memberName: '두번째', ...}}}")
    @ApiParam(value = "해당 프로젝트에 속한 멤버 조회", required = true)
    private List<ProjectMemberRoleResponseDto> projectMemberInfo;

    @ApiModelProperty(name = "projectCity", example = "{'광주', '구미'}")
    @ApiParam(value = "선택할 수 있는 지역 리스트", required = true)
    private List<String> projectCity;

}
