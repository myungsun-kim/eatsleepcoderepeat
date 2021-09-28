package com.ssafy.match.group.repository.study;

import com.ssafy.match.group.entity.study.CompositeMemberStudy;
import com.ssafy.match.group.entity.study.Study;
import com.ssafy.match.group.entity.study.StudyApplicationForm;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudyApplicationFormRepository extends
    JpaRepository<StudyApplicationForm, CompositeMemberStudy> {

    // 모든 신청서 날짜 내림차순 조회
    @Query("select s from StudyApplicationForm s where s.compositeMemberStudy.study = :study order by s.createDate desc ")
    List<StudyApplicationForm> formByStudyId(@Param("study") Study study);

    // 닉네임으로 신청서 조회
    @Query("select p from StudyApplicationForm p "
        + "where p.compositeMemberStudy.study = :study "
        + "and p.nickname like %:nickname% order by p.createDate desc")
    List<StudyApplicationForm> allFormByStudyNickname(@Param("study") Study study, @Param("nickname") String nickname);

    // 신청서 조회
    @Query("select s from StudyApplicationForm s where s.compositeMemberStudy = :cms")
    Optional<StudyApplicationForm> oneFormById(@Param("cms") CompositeMemberStudy cms);

}