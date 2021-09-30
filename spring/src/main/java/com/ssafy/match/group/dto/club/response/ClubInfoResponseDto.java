package com.ssafy.match.group.dto.club.response;

import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.group.dto.MemberDto;
import com.ssafy.match.group.entity.club.Club;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "클럽 수정 정보", description = "클럽 수정을 위한 정보 Response Dto Class")
@Getter
@Setter
public class ClubInfoResponseDto {

    @ApiModelProperty(name = "name", example = "알고리즘 클럽")
    @ApiParam(value = "클럽명", required = true)
    private String name;

    @ApiModelProperty(name = "maxCount", example = "3")
    @ApiParam(value = "최대 인원", required = true)
    private int maxCount;

    @ApiModelProperty(name = "isPublic", example = "false")
    @ApiParam(value = "공개 비공개", required = true)
    private Boolean isPublic;

    @ApiModelProperty(name = "city", example = "구미")
    @ApiParam(value = "지역", required = true)
    private String city;

    @ApiModelProperty(name = "dbFile")
    @ApiParam(value = "사진 정보")
    private DBFile dbFile;

    @ApiModelProperty(name = "bio", example = "매칭 클럽입니다.")
    @ApiParam(value = "클럽 소개", required = true)
    private String bio;

    @ApiModelProperty(name = "host", example = "{\"id\": 3, \"name\": \"박범진\", \"nickname\": \"BJP\"}")
    @ApiParam(value = "클럽장 정보(id, name, nickname)", required = true)
    private MemberDto host;

    @ApiModelProperty(name = "memberDtos", example = "[{\"id\": 3, \"name\": \"문일민\", \"nickname\": \"별명\"}, {\"id\": 4, \"name\": \"박범진\", \"nickname\": \"내별명\"}]")
    @ApiParam(value = "해당 클럽에 속한 멤버 조회", required = true)
    private List<MemberDto> memberDtos;

    @Builder
    public ClubInfoResponseDto(Club club) {
        this.name = club.getName();
        this.maxCount = club.getMaxCount();
        this.isPublic = club.getIsPublic();
        this.city = club.getCity().name();
        this.dbFile = club.getDbFile();
        this.bio = club.getBio();
    }
}
