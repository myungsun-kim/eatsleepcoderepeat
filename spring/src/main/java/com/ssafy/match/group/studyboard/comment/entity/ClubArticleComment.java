package com.ssafy.match.group.studyboard.comment.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "club_article_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    private int depth;

    @Column(name = "parent_id")
    private int parnetId;

    @Column(name = "is_child")
    private Boolean isChild;

    @Column(name = "reply_count")
    private int replyCount;
}
