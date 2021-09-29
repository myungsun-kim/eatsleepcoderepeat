package com.ssafy.match.group.dto;

import com.ssafy.match.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberDto {

    private Long id;
    private String name;
    private String nickname;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.nickname = member.getNickname();
    }
}
