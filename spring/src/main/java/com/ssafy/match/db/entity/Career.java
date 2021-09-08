package com.ssafy.match.db.entity;

import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.career")
public class Career {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int career_id;

    private String company;
    private String department;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    public Career() {
    }

    public Career(int career_id, String company, String department, LocalDateTime start_date,
        LocalDateTime end_date, String description, User user_id) {
        this.career_id = career_id;
        this.company = company;
        this.department = department;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.user_id = user_id;
    }
}