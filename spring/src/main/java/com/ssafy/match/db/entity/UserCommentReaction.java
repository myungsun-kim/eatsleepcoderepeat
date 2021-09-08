package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserCommentReaction;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.user_comment_reaction")
public class UserCommentReaction {

    @EmbeddedId
    private CompositeUserCommentReaction compositeUserCommentReaction;

    private LocalDateTime created_date;

    public UserCommentReaction() {
    }

    public UserCommentReaction(
        CompositeUserCommentReaction compositeUserCommentReaction, LocalDateTime created_date) {
        this.compositeUserCommentReaction = compositeUserCommentReaction;
        this.created_date = created_date;
    }
}