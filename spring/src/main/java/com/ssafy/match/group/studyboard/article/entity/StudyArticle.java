package com.ssafy.match.group.studyboard.article.entity;

import com.ssafy.match.group.studyboard.board.entity.StudyBoard;
import com.ssafy.match.member.entity.Member;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
