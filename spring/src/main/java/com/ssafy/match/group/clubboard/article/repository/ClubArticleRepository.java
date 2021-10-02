package com.ssafy.match.group.clubboard.article.repository;


import com.ssafy.match.group.clubboard.article.entity.ClubArticle;
import com.ssafy.match.group.clubboard.board.entity.ClubBoard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubArticleRepository extends JpaRepository<ClubArticle, Long> {
    List<ClubArticle> findAllByClubBoard(ClubBoard clubBoard);
}