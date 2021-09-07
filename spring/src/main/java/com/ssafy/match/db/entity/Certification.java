package com.ssafy.match.db.entity;

import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "matching.certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int certification_id;

    private String name;
    private String organization;
    private String code;
    private String grade;
    private LocalDateTime issued_date;
    private LocalDateTime expired_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    public Certification() {
    }

    public Certification(int certification_id, String name, String organization, String code,
        String grade, LocalDateTime issued_date, LocalDateTime expired_date,
        User user_id) {
        this.certification_id = certification_id;
        this.name = name;
        this.organization = organization;
        this.code = code;
        this.grade = grade;
        this.issued_date = issued_date;
        this.expired_date = expired_date;
        this.user_id = user_id;
    }
}