package com.ssafy.match.group.entity;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
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
@Entity(name = "matching.study")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member member;

    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private int period;
    private String schedule;
    private String bio;
    private int member_count;
    private int max_count;

    @Enumerated(EnumType.STRING)
    private City city;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean is_active;
    private boolean is_public;
    private boolean is_participate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_pic")
    private DBFile dbFile;
//    private int activity_point;
//    private String repository;
//    private String team_chat;

    public Study(String name, Member member, LocalDateTime create_date,
        LocalDateTime modify_date, int period, String schedule, String bio, int member_count,
        int max_count, City city, Status status, boolean is_active, boolean is_public,
        boolean is_participate, Club club, DBFile dbFile) {
        this.name = name;
        this.member = member;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.period = period;
        this.schedule = schedule;
        this.bio = bio;
        this.member_count = member_count;
        this.max_count = max_count;
        this.city = city;
        this.status = status;
        this.is_active = is_active;
        this.is_public = is_public;
        this.is_participate = is_participate;
        this.club = club;
        this.dbFile = dbFile;
    }
}