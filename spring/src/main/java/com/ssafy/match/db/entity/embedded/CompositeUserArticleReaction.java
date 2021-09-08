package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Article;
import com.ssafy.match.db.entity.Reaction;
import com.ssafy.match.db.entity.Member;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompositeUserArticleReaction implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reaction_id")
    private Reaction reaction;

    public CompositeUserArticleReaction(Member member, Article article,
        Reaction reaction) {
        this.member = member;
        this.article = article;
        this.reaction = reaction;
    }
}