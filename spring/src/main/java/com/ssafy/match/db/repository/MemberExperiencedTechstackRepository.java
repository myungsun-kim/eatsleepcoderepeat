package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.MemberExperiencedTechstack;
import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// https://whitekeyboard.tistory.com/313
public interface MemberExperiencedTechstackRepository extends
    JpaRepository<MemberExperiencedTechstack, CompositeMemberTechstack> {
    //@Query(value = "select * from matching.member_experienced_techstack mt")
    @Query(value = "select mt.compositeMemberTechstack.techstack.name from matching.member_experienced_techstack mt where mt.compositeMemberTechstack.member = :member")
    List<String> findByMemberTechstackName(@Param("member") Member member);
//    List<String> findTechstackNameByMember(@Param("member") Member member);
}