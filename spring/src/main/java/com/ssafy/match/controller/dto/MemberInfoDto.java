package com.ssafy.match.controller.dto;

import com.ssafy.match.db.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDto {
    private String email;
    public static MemberInfoDto of(Member member) {
        return new MemberInfoDto(member.getEmail());
    }
}