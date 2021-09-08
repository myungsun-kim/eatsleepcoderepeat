package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Article;
import com.ssafy.match.db.entity.Project;
import com.ssafy.match.db.entity.Reaction;
import com.ssafy.match.db.entity.User;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CompositeUserArticleReaction implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reaction_id")
    private Reaction reaction_id;

    public CompositeUserArticleReaction() {
    }

    public CompositeUserArticleReaction(User user_id, Article article_id,
        Reaction reaction_id) {
        this.user_id = user_id;
        this.article_id = article_id;
        this.reaction_id = reaction_id;
    }
}