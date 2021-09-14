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
@Entity(name = "matching.project")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member member;

    private String host_role;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private String Schedule;
    private int period;
    private String bio;
    private int developer_count;
    private int developer_max_count;
    private int planner_count;
    private int planner_max_count;
    private int designer_count;
    private int designer_max_count;

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

    public void plusDeveloper(){
        developer_count++;
    }

    public void plusPlanner(){
        planner_count++;
    }

    public void plusDesigner(){
        designer_count++;
    }
    public void minusDeveloper(){
        developer_count--;
    }

    public void minusPlanner(){
        planner_count--;
    }

    public void minusDesigner(){
        designer_count--;
    }

    public Project(String name, Member member, String host_role, LocalDateTime create_date,
        LocalDateTime modify_date, String schedule, int period, String bio, int developer_count,
        int developer_max_count, int planner_count, int planner_max_count, int designer_count,
        int designer_max_count, City city, Status status, boolean is_active, boolean is_public,
        boolean is_participate, Club club, DBFile dbFile) {
        this.name = name;
        this.member = member;
        this.host_role = host_role;
        this.create_date = create_date;
        this.modify_date = modify_date;
        Schedule = schedule;
        this.period = period;
        this.bio = bio;
        this.developer_count = developer_count;
        this.developer_max_count = developer_max_count;
        this.planner_count = planner_count;
        this.planner_max_count = planner_max_count;
        this.designer_count = designer_count;
        this.designer_max_count = designer_max_count;
        this.city = city;
        this.status = status;
        this.is_active = is_active;
        this.is_public = is_public;
        this.is_participate = is_participate;
        this.club = club;
        this.dbFile = dbFile;
    }
}