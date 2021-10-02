package com.ssafy.match.group.studyboard.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.match.group.studyboard.board.entity.StudyBoard;
import com.ssafy.match.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "matching.study_article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_board_id")
    private StudyBoard studyBoard;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "view_count")
    private Integer viewCount;

    @Builder
    public StudyArticle(StudyBoard studyBoard, Member member, String title, LocalDateTime createDate, LocalDateTime modifiedDate, Integer viewCount) {
        this.studyBoard = studyBoard;
        this.member = member;
        this.title = title;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.viewCount = viewCount;
    }
}
