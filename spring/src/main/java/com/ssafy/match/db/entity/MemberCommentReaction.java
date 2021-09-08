package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserCommentReaction;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.member_comment_reaction")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCommentReaction {

    @EmbeddedId
    private CompositeUserCommentReaction compositeUserCommentReaction;

    private LocalDateTime created_date;

    public MemberCommentReaction(
        CompositeUserCommentReaction compositeUserCommentReaction, LocalDateTime created_date) {
        this.compositeUserCommentReaction = compositeUserCommentReaction;
        this.created_date = created_date;
    }
}