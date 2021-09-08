package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.UserCommentReaction;
import com.ssafy.match.db.entity.embedded.CompositeUserCommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentReactionRepository extends
    JpaRepository<UserCommentReaction, CompositeUserCommentReaction> {

}