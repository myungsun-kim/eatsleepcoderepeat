package com.ssafy.match.group.dto.study.Response;

import com.ssafy.match.group.dto.club.ClubDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "스터디 생성을 위해 필요한 정보", description = "모든 기술 스택 리스트, 호스트가 가진 클럽 정보, 지역 리스트를 가진 Response Dto Class")
@Getter
@Builder
public class StudyInfoForCreateResponseDto {

    @ApiModelProperty(name = "allTechstack", example = "['Java', 'Python', 'Spring', ...]")
    @ApiParam(value = "모든 기술스택 목록", required = true)
    private List<String> allTechstack;

    @ApiModelProperty(name = "hostClub", example = "{[id: 1, name: '클럽1'], [id: 2, name: '클럽2']}")
    @ApiParam(value = "스터디를 생성하려는 멤버의 클럽 id, name 정보 리스트", required = true)
    private List<ClubDto> hostClub;

    @ApiModelProperty(name = "city", example = "{'광주', '구미', ...}")
    @ApiParam(value = "선택할 수 있는 지역 리스트", required = true)
    private List<String> city;

}
