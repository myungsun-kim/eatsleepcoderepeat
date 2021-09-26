package com.ssafy.match.group.dto.project.response;

import com.ssafy.match.group.entity.club.Club;
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

    @ApiModelProperty(name = "allTechstack", example = "{'Java', 'Python', 'Spring'}")
    @ApiParam(value = "모든 기술스택 목록", required = true)
    private List<String> allTechstack;

    @ApiModelProperty(name = "hostClub", example = "{{clubId: 1, clubName: '첫번째', ...}, {clubId: 2, clubName: '두번째', ...}}")
    @ApiParam(value = "해당 프로젝트 호스트가 포함되어있는 클럽 목록 (수정시 클럽 수정을 위한)", required = true)
    private List<Club> hostClub;

    @ApiModelProperty(name = "projectCity", example = "{'광주', '구미'}")
    @ApiParam(value = "선택할 수 있는 지역 리스트", required = true)
    private List<String> projectCity;

}
