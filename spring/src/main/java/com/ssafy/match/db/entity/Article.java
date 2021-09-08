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
@Entity(name = "matching.article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int article_id;

    private String title;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private boolean is_deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    public Article() {
    }

    public Article(int article_id, String title, String content, LocalDateTime create_date,
        LocalDateTime modify_date, boolean is_deleted, Board board_id,
        User user_id) {
        this.article_id = article_id;
        this.title = title;
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.is_deleted = is_deleted;
        this.board_id = board_id;
        this.user_id = user_id;
    }
}