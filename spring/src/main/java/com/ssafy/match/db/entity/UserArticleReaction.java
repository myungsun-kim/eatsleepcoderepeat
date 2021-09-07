package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserArticleReaction;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.user_article_reaction")
public class UserArticleReaction {

    @EmbeddedId
    private CompositeUserArticleReaction compositeUserArticleReaction;

    private LocalDateTime created_date;

    public UserArticleReaction() {
    }

    public UserArticleReaction(
        CompositeUserArticleReaction compositeUserArticleReaction, LocalDateTime created_date) {
        this.compositeUserArticleReaction = compositeUserArticleReaction;
        this.created_date = created_date;
    }
}