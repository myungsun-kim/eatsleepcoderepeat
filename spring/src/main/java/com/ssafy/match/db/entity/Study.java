package com.ssafy.match.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.study")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDateTime create_date;
    private String bio;
    private int member_count;
    private int max_count;
    private int activity_point;
    private String repository;
    private String team_chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    private boolean is_active;
    private boolean is_participate;

    public Study(String name, LocalDateTime create_date, String bio, int member_count,
        int max_count,
        int activity_point, String repository, String team_chat, Club club, boolean is_active,
        boolean is_participate) {
        this.name = name;
        this.create_date = create_date;
        this.bio = bio;
        this.member_count = member_count;
        this.max_count = max_count;
        this.activity_point = activity_point;
        this.repository = repository;
        this.team_chat = team_chat;
        this.club = club;
        this.is_active = is_active;
        this.is_participate = is_participate;
    }
}