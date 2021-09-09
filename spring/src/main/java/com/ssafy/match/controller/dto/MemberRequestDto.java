package com.ssafy.match.controller.dto;

import com.ssafy.match.db.entity.Authority;
import com.ssafy.match.db.entity.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String email;
    private String password;
    private LocalDateTime create_date;
    private String name;
    private String nickname;
    private String tel;
    private String bio;
    private String cover_pic;
    private String city;
    private Boolean banned;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .banned(banned)
                .city(city)
                .tel(tel)
                .nickname(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}