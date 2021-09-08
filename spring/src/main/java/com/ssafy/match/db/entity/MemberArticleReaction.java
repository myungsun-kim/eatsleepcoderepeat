package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserArticleReaction;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.member_article_reaction")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberArticleReaction {

    @EmbeddedId
    private CompositeUserArticleReaction compositeUserArticleReaction;

    private LocalDateTime created_date;

    public MemberArticleReaction(
        CompositeUserArticleReaction compositeUserArticleReaction, LocalDateTime created_date) {
        this.compositeUserArticleReaction = compositeUserArticleReaction;
        this.created_date = created_date;
    }
}