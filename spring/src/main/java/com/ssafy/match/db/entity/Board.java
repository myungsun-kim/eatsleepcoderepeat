package com.ssafy.match.db.entity;

import java.time.LocalDateTime;
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
@Entity(name = "matching.board")
public class Board {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int board_id;

    private String name;
    private LocalDateTime create_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study_id;

    public Board() {
    }

    public Board(int board_id, String name, LocalDateTime create_date,
        Club club_id, Project project_id, Study study_id) {
        this.board_id = board_id;
        this.name = name;
        this.create_date = create_date;
        this.club_id = club_id;
        this.project_id = project_id;
        this.study_id = study_id;
    }
}