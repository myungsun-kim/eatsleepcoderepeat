package com.ssafy.match.group.dto.club;

import com.ssafy.match.db.entity.City;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

@Getter
public class ClubRequestDto {

    @ApiModelProperty(name = "name", example = "SSAFY 클럽")
    @ApiParam(value = "클럽명", required = true)
    private String name;

    @ApiModelProperty(name = "bio", example = "SSAFY 사람들이 모이는 클럽입니다.")
    @ApiParam(value = "클럽 소개", required = true)
    private String bio;

    @ApiModelProperty(name = "city", example = "구미")
    @ApiParam(value = "활동지역", required = true)
    private City city;

    @ApiModelProperty(name = "host_name", example = "5")
    @ApiParam(value = "클럽장", required = true)
    private Long host_id;

    @ApiModelProperty(name = "max_count", example = "10")
    @ApiParam(value = "제한 인원", required = true)
    private int max_count;

    @ApiModelProperty(name = "is_public", example = "false")
    @ApiParam(value = "공개 비공개", required = true)
    private boolean is_public;

    @ApiModelProperty(name = "uuid", example = "3fads23-fdfd13-23d2")
    @ApiParam(value = "사진 고유 uuid", required = true)
    private String uuid;

//    @ApiModelProperty(name = "data")
//    @ApiParam(value = "사진 데이터", required = true)
//    private byte[] data;
}
