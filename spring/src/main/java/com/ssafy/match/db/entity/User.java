package com.ssafy.match.db.entity;

import lombok.Data;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity(name="matching.user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String email;
    private String name;
    private String password;
    private String nickname;
    private String tel;
    private String twitter;
    private String git;
    private String facebook;
    private String baekjoon;
    private String blog;
    private LocalDateTime created_at;
    private String bio;
    private String cover_pic;
    private String portfolio_path;
}