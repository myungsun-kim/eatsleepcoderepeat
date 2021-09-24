package com.ssafy.match.controller.dto;

import com.ssafy.match.db.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String email;
    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail());
    }
}
