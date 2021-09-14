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

    @OneToOne
    @JoinColumn(name = "cover_pic")
    private DBFile dbFile;
    private String city;
    private Boolean banned;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(LocalDateTime create_date, String email, String name, String password,
        String nickname, String tel, String bio, DBFile dbFile, String city,
        Boolean banned, Authority authority) {
        this.create_date = create_date;
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
        this.bio = bio;
        this.dbFile = dbFile;
        this.city = city;
        this.banned = banned;
        this.authority = authority;
    }

}