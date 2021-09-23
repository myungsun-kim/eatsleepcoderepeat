package com.ssafy.match.group.entity.club;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.file.entity.DBFile;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.club")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member member;
    private LocalDateTime create_date;
    private String bio;

    @Enumerated(EnumType.STRING)
    private City city;
    private int member_count;
    private int max_count;
    private boolean is_public;
    private boolean is_active;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_pic")
    private DBFile dbFile;
//    private int activity_point;
//    private String repository;
//    private String team_chat;

    public Club(String name, Member member, LocalDateTime create_date, String bio,
        City city, int member_count, int max_count, boolean is_public, boolean is_active,
        DBFile dbFile) {
        this.name = name;
        this.member = member;
        this.create_date = create_date;
        this.bio = bio;
        this.city = city;
        this.member_count = member_count;
        this.max_count = max_count;
        this.is_public = is_public;
        this.is_active = is_active;
        this.dbFile = dbFile;
    }
}