package com.ssafy.match.group.projectboard.article.repository;


import com.ssafy.match.group.projectboard.article.entity.ProjectArticle;
import com.ssafy.match.group.projectboard.board.entity.ProjectBoard;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectArticleRepository extends JpaRepository<ProjectArticle, Long> {
    Page<ProjectArticle> findAllByProjectBoard(ProjectBoard projectBoard, Pageable pageable);
}
