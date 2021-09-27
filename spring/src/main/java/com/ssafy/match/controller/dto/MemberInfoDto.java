package com.ssafy.match.controller.dto;

import com.ssafy.match.db.entity.*;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.project.Project;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberInfoDto {
    @ApiModelProperty(name = "email", example = "my_email@gmail.com")
    private String email;

    @ApiModelProperty(name = "name", example = "문일민")
    private String name;

    @ApiModelProperty(name = "nickname", example = "별명")
    private String nickname;

    @ApiModelProperty(name = "tel", example = "010-1234-4567")
    private String tel;

    @ApiModelProperty(name = "bio", example = "let me introduce")
    private String bio;

    @ApiModelProperty(name = "city", example = "부산")
    private String city;

    @ApiModelProperty(name = "position", example = "개발자")
    private String position;

    @ApiModelProperty(name = "cover_pic", example = "사진 데이터")
    private DBFile cover_pic;

    @ApiModelProperty(name = "portfolio", example = "사진 데이터")
    private DBFile portfolio;

    @ApiModelProperty(name = "portfolio_uri", example = "https://naver.com")
    private String portfolio_uri;

//    @ApiModelProperty(name = "myClubList", example = "[\"\",\"java\"]")
    private List<Club> myClubList = new ArrayList<>();
    @ApiModelProperty(name = "myProjectList", example = "[\"python\",\"java\"]")
    private List<Project> myProjectList = new ArrayList<>();
    @ApiModelProperty(name = "expTechList", example = "[\"python\",\"java\"]")
    private List<String> expTechList = new ArrayList<>();
    @ApiModelProperty(name = "beginTechList", example = "[\"python\",\"java\"]")
    private List<String> beginTechList = new ArrayList<>();
    @ApiModelProperty(name = "snsList", example = "[\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"snsName\": \"github\",\n" +
            "      \"snsAccount\": \"gitid\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"snsName\": \"backjoon\",\n" +
            "      \"snsAccount\": \"bjid\"\n" +
            "    }\n" +
            "  ]")
    private List<MemberSns> snsList = new ArrayList<>();
    @ApiModelProperty(name = "dpositionList", example = "[\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"frontend\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"name\": \"devops\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7,\n" +
            "      \"name\": \"testpostion\"\n" +
            "    }\n" +
            "  ]")
    private List<Position> dpositionList = new ArrayList<>();

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
//                .cover_pic(member.getCover_pic())
//                .portfolio(member.getPortfolio_uuid())
                .portfolio_uri(member.getPortfolio_uri())
                .build();
    }

    @Builder
    public MemberInfoDto(String email, String name, String nickname, String tel, String bio, String city, String position, DBFile cover_pic, DBFile portfolio, String portfolio_uri) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.tel = tel;
        this.bio = bio;
        this.city = city;
        this.position = position;
        this.cover_pic = cover_pic;
        this.portfolio = portfolio;
        this.portfolio_uri = portfolio_uri;
//        this.myClubList = myClubList;
    }

}