package com.ssafy.match.group.repository.study;

import com.ssafy.match.member.entity.Member;
import com.ssafy.match.group.entity.study.CompositeMemberStudy;
import com.ssafy.match.group.entity.study.MemberStudy;
import com.ssafy.match.group.entity.study.Study;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberStudyRepository extends JpaRepository<MemberStudy, CompositeMemberStudy> {
    // 특정 스터디에 속한 멤버의 관계 정보
    @Query(value = "select ms from matching.member_study ms "
        + "where ms.compositeMemberStudy.study = :study and ms.isActive = true")
    List<MemberStudy> findMemberRelationInStudy(@Param("study") Study study);

    // 특정 스터디에 속한 멤버의 정보
    @Query(value = "select ms.compositeMemberStudy.member from matching.member_study ms "
        + "where ms.compositeMemberStudy.study = :study and ms.isActive = true")
    List<Member> findMemberInStudy(@Param("study") Study study);

    // 특정 멤버가 가지고 있는 활성화 스터디
    @Query(value = "select ms.compositeMemberStudy.study from matching.member_study ms "
        + "where ms.compositeMemberStudy.member = :member and ms.isActive = true")
    List<Study> studyInMember(@Param("member") Member member);

    @Query(value = "select ms.compositeMemberStudy.member from matching.member_study ms "
            + "where ms.compositeMemberStudy.study.id = :id and ms.isActive = true")
    List<Member> findMemberByStudyId(@Param("id") Long id);
}