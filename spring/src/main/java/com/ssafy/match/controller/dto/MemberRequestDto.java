package com.ssafy.match.controller.dto;

import com.ssafy.match.db.entity.Authority;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.MemberExperiencedTechstack;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String position;
    private Boolean is_active;
    private String portfolio_uri;
    private List<String> expTechList;
    private List<String> beginTechList;
    private List<String> dpositionList;

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
//    public MemberExperiencedTechstack toMemberExperiencedTechstack(Member member, Techstack techstack) {
//        CompositeMemberTechstack compositeMemberTechstack = toCompositeMemberTechstack(member, techstack);
//        return MemberExperiencedTechstack.builder()
//                .compositeMemberTechstack(compositeMemberTechstack)
//                .build();
//    }
//    public CompositeMemberTechstack toCompositeMemberTechstack(Member member, Techstack techstack) {
//        return CompositeMemberTechstack
//                .builder()
//                .member(member)
//                .teckstack(techstack)
//                .build();
//    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}