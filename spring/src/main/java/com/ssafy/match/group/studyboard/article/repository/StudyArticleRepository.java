package com.ssafy.match.group.studyboard.article.repository;


import com.ssafy.match.group.studyboard.article.entity.StudyArticle;
import com.ssafy.match.group.studyboard.board.entity.StudyBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyArticleRepository extends JpaRepository<StudyArticle, Long> {
    Page<StudyArticle> findAllByStudyBoard(StudyBoard studyBoard, Pageable pageable);

    // 제목으로 게시글 조회
    @Query("select sa from matching.study_article sa "
        + "where sa.studyBoard = :studyBoard "
        + "and sa.title like %:title% order by sa.createDate desc")
    Page<StudyArticle> findAllByStudyBoardAndTitle(@Param("studyBoard") StudyBoard studyBoard, @Param("title") String title, Pageable pageable);

    // 닉네임으로 게시글 조회
    @Query("select sa from matching.study_article sa "
        + "where sa.studyBoard = :studyBoard "
        + "and sa.member.nickname like %:nickname% order by sa.createDate desc")
    Page<StudyArticle> findAllByStudyBoardAndNickname(@Param("studyBoard") StudyBoard studyBoard, @Param("nickname") String nickname, Pageable pageable);
}
