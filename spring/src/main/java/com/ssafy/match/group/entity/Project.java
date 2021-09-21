package com.ssafy.match.group.entity;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.file.entity.DBFile;
import java.time.LocalDateTime;
import javax.persistence.Column;
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
import lombok.Builder;
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

    @Column(name = "host_role")
    private String hostRole;
    @Column(name = "create_Date")
    private LocalDateTime createDate;
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;
    private String schedule;
    private int period;
    private String bio;
    @Column(name = "developer_count")
    private int developerCount;
    @Column(name = "developer_max_count")
    private int developerMaxCount;
    @Column(name = "planner_count")
    private int plannerCount;
    @Column(name = "planner_max_count")
    private int plannerMaxCount;
    @Column(name = "designer_count")
    private int designerCount;
    @Column(name = "designer_max_count")
    private int designerMaxCount;

    @Enumerated(EnumType.STRING)
    private City city;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "is_public")
    private boolean isPublic;
    @Column(name = "is_participate")
    private boolean isParticipate;

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
        developerCount++;
    }

    public void plusPlanner(){
        plannerCount++;
    }

    public void plusDesigner(){
        designerCount++;
    }
    public void minusDeveloper(){
        developerCount--;
    }

    public void minusPlanner(){
        plannerCount--;
    }

    public void minusDesigner(){
        designerCount--;
    }

    @Builder
    public Project(String name, Member member, LocalDateTime createDate,
        LocalDateTime modifyDate, String schedule, int period, String bio, int developerCount,
        int developerMaxCount, int plannerCount, int plannerMaxCount, int designerCount,
        int designerMaxCount, City city, Status status, boolean isActive, boolean isPublic,
        boolean isParticipate) {
        this.name = name;
        this.member = member;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.schedule = schedule;
        this.period = period;
        this.bio = bio;
        this.developerCount = developerCount;
        this.developerMaxCount = developerMaxCount;
        this.plannerCount = plannerCount;
        this.plannerMaxCount = plannerMaxCount;
        this.designerCount = designerCount;
        this.designerMaxCount = designerMaxCount;
        this.city = city;
        this.status = status;
        this.isActive = isActive;
        this.isPublic = isPublic;
        this.isParticipate = isParticipate;
    }
}