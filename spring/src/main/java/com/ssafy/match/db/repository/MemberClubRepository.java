package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.MemberClub;
import com.ssafy.match.db.entity.embedded.CompositeMemberClub;
import com.ssafy.match.group.entity.club.Club;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberClubRepository extends JpaRepository<MemberClub, CompositeMemberClub> {
    // 해당 멤버가 속한 클럽 리스트
    @Query(value = "select mc.compositeMemberClub.club from matching.member_club mc "
        + "where mc.compositeMemberClub.member = :member and mc.is_active = true")
    List<Club> findClubByMember(@Param("member") Member member);
}