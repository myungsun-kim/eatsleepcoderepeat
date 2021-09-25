package com.ssafy.match.controller.dto;


import com.ssafy.match.db.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateResponseDto {
    private Long id;

    public static MemberUpdateResponseDto of(Long id) {
        return new MemberUpdateResponseDto(id);
    }
}
