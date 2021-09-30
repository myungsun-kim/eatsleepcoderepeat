package com.ssafy.match.group.studyboard.article.repository;


import com.ssafy.match.group.studyboard.article.entity.StudyArticle;
import com.ssafy.match.group.studyboard.board.entity.StudyBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyArticleRepository extends JpaRepository<StudyArticle, Long> {
    List<StudyArticle> findAllByStudyBoard(StudyBoard studyBoard);
}
