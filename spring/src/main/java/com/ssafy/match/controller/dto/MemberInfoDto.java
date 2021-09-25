package com.ssafy.match.controller.dto;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.MemberExperiencedTechstack;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.project.Project;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberInfoDto {
    @ApiModelProperty(name = "email", example = "my_email@gmail.com")
//    @ApiParam(value = "이메일", required = true)
    private String email;

    @ApiModelProperty(name = "name", example = "문일민")
//    @ApiParam(value = "이름", required = true)
    private String name;

    @ApiModelProperty(name = "nickname", example = "별명")
//    @ApiParam(value = "별명", required = true)
    private String nickname;

    @ApiModelProperty(name = "tel", example = "010-1234-4567")
//    @ApiParam(value = "전화번호", required = false)
    private String tel;

    @ApiModelProperty(name = "bio", example = "let me introduce")
//    @ApiParam(value = "자기소개", required = false)
    private String bio;

    @ApiModelProperty(name = "city", example = "부산")
//    @ApiParam(value = "도시", required = false)
    private String city;

    @ApiModelProperty(name = "position", example = "개발자")
//    @ApiParam(value = "역할", required = true)
    private String position;

    @ApiModelProperty(name = "picture", example = "사진 데이터")
//    @ApiParam(value = "사진", required = false)
    private DBFile dbFile;

//    @ApiModelProperty(name = "myClubList", example = "{{clubId: 1, clubName: '클럽 A', ...}, {clubId: 2, clubName: '클럽 B', ...}}")
//    @ApiParam(value = "내가 속한 클럽 목록", required = true)
    private List<Club> myClubList = new ArrayList<>();
    private List<Project> myProjectList = new ArrayList<>();
    private List<String> experiencedList = new ArrayList<>();

//    public MemberInfoDto(Member member) {
//        email = member.getEmail();
//        name = member.getName();
//        nickname = member.getNickname();
//        tel = member.getTel();
//        bio = member.getBio();
//        city = member.getCity();
//        position = member.getPosition();
//        dbFile = member.getDbFile();
//    }
    public static MemberInfoDto of(Member member) {
        return MemberInfoDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .tel(member.getTel())
                .bio(member.getBio())
                .city(member.getCity())
                .position(member.getPosition())
                .dbFile(member.getDbFile())
                .build();
    }

    @Builder
    public MemberInfoDto(String email, String name, String nickname, String tel, String bio, String city, String position, DBFile dbFile) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.tel = tel;
        this.bio = bio;
        this.city = city;
        this.position = position;
        this.dbFile = dbFile;
//        this.myClubList = myClubList;
    }

}