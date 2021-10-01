package com.ssafy.match.group.studyboard.article.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.match.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "matching.study_content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_article_id")
    private Member memberId;

    @Column(name = "content")
    private String content;

    @Builder
    public StudyContent(String content) {
        this.content = content;
    }

}
