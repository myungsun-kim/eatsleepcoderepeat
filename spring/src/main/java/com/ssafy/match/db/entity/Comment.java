package com.ssafy.match.db.entity;

import java.time.LocalDateTime;
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
@Entity(name = "matching.comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int comment_id;

    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private boolean is_deleted;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    public Comment() {
    }

    public Comment(int comment_id, String content, LocalDateTime create_date,
        LocalDateTime modify_date, boolean is_deleted, Article article_id,
        User user_id) {
        this.comment_id = comment_id;
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.is_deleted = is_deleted;
        this.article_id = article_id;
        this.user_id = user_id;
    }
}