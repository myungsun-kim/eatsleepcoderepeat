package com.ssafy.match.group.studyboard.comment.repository;

import com.ssafy.match.group.studyboard.article.entity.StudyArticle;
import com.ssafy.match.group.studyboard.comment.entity.StudyArticleComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudyArticleCommentRepository extends JpaRepository<StudyArticleComment, Long> {

    @Query("select sac from StudyArticleComment sac order by sac.parentId, sac.createDate")
    List<StudyArticleComment> allComment(@Param("studyArticle") StudyArticle studyArticle);
}