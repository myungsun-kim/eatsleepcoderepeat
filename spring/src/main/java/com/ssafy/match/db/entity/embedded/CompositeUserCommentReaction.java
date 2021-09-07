package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Comment;
import com.ssafy.match.db.entity.Reaction;
import com.ssafy.match.db.entity.User;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CompositeUserCommentReaction implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment_id;

    @ManyToOne
    @JoinColumn(name = "reaction_id")
    private Reaction reaction_id;

    public CompositeUserCommentReaction() {
    }

    public CompositeUserCommentReaction(User user_id, Comment comment_id,
        Reaction reaction_id) {
        this.user_id = user_id;
        this.comment_id = comment_id;
        this.reaction_id = reaction_id;
    }
}