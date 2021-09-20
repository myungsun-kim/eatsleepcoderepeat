package com.ssafy.match.db.entity;

import com.ssafy.match.file.entity.DBFile;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity(name = "matching.member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime create_date;
    private String email;
    private String name;
    private String password;
    private String nickname;
    private String tel;
    private String bio;
    private String city;
    private Boolean banned;
    private String position;
    private Boolean is_active;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_pic")
    private DBFile dbFile;

    @Builder
    public Member(LocalDateTime create_date, String email, String name, String password,
        String nickname, String tel, String bio, String city, Boolean banned, String position,
        Boolean is_active, Authority authority, DBFile dbFile) {
        this.create_date = create_date;
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
        this.bio = bio;
        this.city = city;
        this.banned = banned;
        this.position = position;
        this.is_active = is_active;
        this.authority = authority;
        this.dbFile = dbFile;
    }
}