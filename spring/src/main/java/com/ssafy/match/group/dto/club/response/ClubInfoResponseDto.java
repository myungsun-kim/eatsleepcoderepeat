package com.ssafy.match.group.dto.club.response;

import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.group.dto.MemberDto;
import com.ssafy.match.group.entity.club.Club;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "클럽 수정 정보", description = "클럽 수정을 위한 정보 Response Dto Class")
@Getter
@Setter
public class ClubInfoResponseDto {

    @ApiModelProperty(name = "id", example = "4")
    private Long id;

    @ApiModelProperty(name = "name", example = "알고리즘 클럽")
    private String name;

    @ApiModelProperty(name = "maxCount", example = "3")
    private int maxCount;

    @ApiModelProperty(name = "isPublic", example = "false")
    private Boolean isPublic;

    @ApiModelProperty(name = "city", example = "구미")
    private String city;

    @ApiModelProperty(name = "data", example = "사진을 보이기 위한 바이트")
    @Lob // DBFile 객체 반환시 InvalidDefinitionException: No serializer found for class
    private byte[] data;

    @ApiModelProperty(name = "bio", example = "매칭 클럽입니다.")
    private String bio;

    @ApiModelProperty(name = "host", example = "{\"id\": 3, \"name\": \"박범진\", \"nickname\": \"BJP\"}")
    private MemberDto host;

    @ApiModelProperty(name = "memberDtos", example = "[{\"id\": 3, \"name\": \"문일민\", \"nickname\": \"별명\"}, {\"id\": 4, \"name\": \"박범진\", \"nickname\": \"내별명\"}]")
    private List<MemberDto> memberDtos;

    // 사진 보여줄 때 사용
    public void setData(DBFile dbFile){
        if(dbFile == null) return;
        this.data = dbFile.getData();
    }

    @Builder
    public ClubInfoResponseDto(Club club) {
        this.id = club.getId();
        this.name = club.getName();
        this.maxCount = club.getMaxCount();
        this.isPublic = club.getIsPublic();
        this.city = club.getCity().name();
        this.bio = club.getBio();
        setData(club.getDbFile());
    }
}
