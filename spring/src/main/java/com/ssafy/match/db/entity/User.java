package com.ssafy.match.db.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    private LocalDateTime created_date;
    private String email;
    private String name;
    private String password;
    private String nickname;
    private String tel;
    private String bio;
    private String cover_pic;

    private String city;
    private Boolean banned;

    public User() {
    }

    public User(LocalDateTime created_date, String email, String name, String password,
        String nickname, String tel, String bio, String cover_pic, Boolean banned) {
        this.created_date = created_date;
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
        this.bio = bio;
        this.cover_pic = cover_pic;
        this.banned = banned;
    }
}