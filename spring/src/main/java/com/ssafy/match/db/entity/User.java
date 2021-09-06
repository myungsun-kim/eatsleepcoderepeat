package com.ssafy.match.db.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String email;
    private String name;
    private String password;
    private String nickname;
    private String tel;
    private String twitter;
    private String git;
    private String facebook;
    private String backjoon;
    private String blog;
    private LocalDateTime create_at;
    private String bio;
    private String cover_pic;
    private String portfolio_path;
}