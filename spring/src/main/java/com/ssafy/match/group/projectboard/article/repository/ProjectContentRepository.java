package com.ssafy.match.group.projectboard.article.repository;


import com.ssafy.match.group.projectboard.article.entity.ProjectArticle;
import com.ssafy.match.group.projectboard.article.entity.ProjectContent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectContentRepository extends JpaRepository<ProjectContent, Long> {
    Optional<ProjectContent> getByProjectArticle(ProjectArticle projectArticle);
}
