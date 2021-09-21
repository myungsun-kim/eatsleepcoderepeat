package com.ssafy.match.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberModifyRequestDto {

    private String nickname;
    private String bio;
    private String uuid;
    private String city;

}