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
@Entity(name = "matching.education")
public class Education {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int education_id;

    private String institution;
    private String degree;
    private String major;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private float my_credit;
    private float full_credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    public Education() {
    }

    public Education(int education_id, String institution, String degree, String major,
        LocalDateTime start_date, LocalDateTime end_date, float my_credit, float full_credit,
        User user_id) {
        this.education_id = education_id;
        this.institution = institution;
        this.degree = degree;
        this.major = major;
        this.start_date = start_date;
        this.end_date = end_date;
        this.my_credit = my_credit;
        this.full_credit = full_credit;
        this.user_id = user_id;
    }
}