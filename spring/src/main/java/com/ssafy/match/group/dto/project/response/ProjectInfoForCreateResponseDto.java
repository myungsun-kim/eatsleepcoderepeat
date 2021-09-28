package com.ssafy.match.group.dto.project.response;

import com.ssafy.match.group.dto.club.ClubDto;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectInfoForCreateResponseDto {

    @ApiModelProperty(name = "hostClub", example = "{[id: 1, name: '클럽1'], [id: 2, name: '클럽2']}")
    @ApiParam(value = "프로젝트를 생성하려는 멤버의 클럽 id, name 정보 리스트", required = true)
    private List<ClubDto> hostClub;

    @ApiModelProperty(name = "city", example = "{'광주', '구미'}")
    @ApiParam(value = "선택할 수 있는 지역 리스트", required = true)
    private List<String> city;

}
