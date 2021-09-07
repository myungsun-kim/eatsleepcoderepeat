package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.UserArticleReaction;
import com.ssafy.match.db.entity.embedded.CompositeUserArticleReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserArticleReactionRepository extends
    JpaRepository<UserArticleReaction, CompositeUserArticleReaction> {

}