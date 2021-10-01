package com.ssafy.match.group.dto.study.response;

import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.group.dto.MemberDto;
import com.ssafy.match.group.dto.club.ClubDto;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.study.Study;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "스터디 조회 정보", description = "스터디의 상세 정보 Response Dto Class")
@Getter
@Setter
public class StudyInfoResponseDto {

    @ApiModelProperty(name = "id", example = "4")
    private Long id;

    @ApiModelProperty(name = "name", example = "알고리즘 스터디")
    private String name;

    @ApiModelProperty(name = "schedule", example = "매주 화, 수 6시")
    private String schedule;

    @ApiModelProperty(name = "period", example = "7")
    private int period;

    @ApiModelProperty(name = "host", example = "[\"id\": 3, \"name\": \"문일민\", \"nickname\": \"장난꾸러기\"]")
    private MemberDto host;

    @ApiModelProperty(name = "memberCount", example = "3")
    private int memberCount;

    @ApiModelProperty(name = "maxCount", example = "3")
    private int maxCount;

    @ApiModelProperty(name = "isPublic", example = "false")
    private Boolean isPublic;

    @ApiModelProperty(name = "isParticipate", example = "false")
    private Boolean isParticipate;

    @ApiModelProperty(name = "city", example = "서울")
    private String city;

    @ApiModelProperty(name = "status", example = "모집, 진행, 종료 중 하나")
    @ApiParam(value = "스터디 상태", required = true)
    private String status;

    @ApiModelProperty(name = "club", example = "[\"id\": 3, \"name\": \"SSAFY\"]")
    private ClubDto club;

    @ApiModelProperty(name = "data", example = "사진을 보이기 위한 바이트")
    @Lob // DBFile 객체 반환시 InvalidDefinitionException: No serializer found for class
    private byte[] data;

    @ApiModelProperty(name = "modifyDate", example = "2021-09-06 06:57:37.667537")
    private LocalDateTime modifyDate;

    @ApiModelProperty(name = "bio", example = "알고리즘 스터디입니다.")
    private String bio;

    @ApiModelProperty(name = "projectMember", example = "[{\"id\": 3, \"name\": \"문일민\", \"nickname\": \"별명\"}, {\"id\": 4, \"name\": \"박범진\", \"nickname\": \"내별명\"}]")
    private List<MemberDto> memberDtos;

    @ApiModelProperty(name = "techList", example = "[\"java\", \"python\"]")
    private List<String> techList;

    public void setClub(Club club){
        if(club == null) return;
        this.club = new ClubDto(club);
    }

    public void setData(DBFile dbFile){
        if(dbFile == null) return;
        this.data = dbFile.getData();
    }

    @Builder
    public StudyInfoResponseDto(Study study) {
        this.id = study.getId();
        this.name = study.getName();
        this.schedule = study.getSchedule();
        this.period = study.getPeriod();
        this.memberCount = study.getMemberCount();
        this.maxCount = study.getMaxCount();
        this.isPublic = study.getIsPublic();
        this.isParticipate = study.getIsParticipate();
        this.city = study.getCity().name();
        this.status = study.getStatus().name();
        setClub(study.getClub());
        setData(study.getDbFile());
        this.modifyDate = study.getModifyDate();
        this.bio = study.getBio();
    }
}
