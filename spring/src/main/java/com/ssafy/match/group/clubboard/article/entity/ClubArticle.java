package com.ssafy.match.group.clubboard.article.entity;

import com.ssafy.match.group.clubboard.article.dto.ClubArticleCreateRequestDto;
import com.ssafy.match.group.clubboard.board.entity.ClubBoard;
import com.ssafy.match.member.entity.Member;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "club_article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "club_board_id")
    private ClubBoard clubBoard;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "view_count")
    private int viewCount;

    @Builder
    public ClubArticle(ClubArticleCreateRequestDto dto, ClubBoard clubBoard, Member member) {
        this.clubBoard = clubBoard;
        this.member = member;
        this.title = dto.getTitle();
        this.createDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
        this.viewCount = 0;
    }
}
