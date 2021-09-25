package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.MemberBeginnerTechstack;
import com.ssafy.match.db.entity.MemberExperiencedTechstack;
import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberBeginnerTechstackRepository extends
        JpaRepository<MemberBeginnerTechstack, CompositeMemberTechstack> {
    @Query(value = "select mt.compositeMemberTechstack.techstack.name from matching.member_beginner_techstack mt where mt.compositeMemberTechstack.member = :member")
    List<String> findTechstackByMemberName(@Param("member") Member member);
}
