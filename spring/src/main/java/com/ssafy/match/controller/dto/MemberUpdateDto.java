package com.ssafy.match.controller.dto;


import com.ssafy.match.db.entity.Authority;
import com.ssafy.match.db.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {

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
    private String position;
    private Boolean is_active;
    private String portfolio_uri;
    private List<String> expTechList;
    private List<String> beginTechList;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .create_date(LocalDateTime.now())
                .name(name)
                .nickname(nickname)
                .tel(tel)
                .bio(bio)
                .city(city)
                .banned(Boolean.FALSE)
                .position(position)
                .is_active(Boolean.TRUE)
                .authority(Authority.ROLE_USER)
                .portfolio_uri(portfolio_uri)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
