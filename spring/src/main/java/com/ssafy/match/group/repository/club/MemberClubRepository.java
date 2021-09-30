package com.ssafy.match.group.repository.club;

import com.ssafy.match.group.dto.MemberDto;
import com.ssafy.match.group.dto.club.ClubDto;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.club.CompositeMemberClub;
import com.ssafy.match.group.entity.club.MemberClub;
import com.ssafy.match.member.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberClubRepository extends JpaRepository<MemberClub, CompositeMemberClub> {
    // 특정 클럽에 속한 멤버의 관계 정보
    @Query(value = "select mc from matching.member_club mc "
        + "where mc.compositeMemberClub.club = :club and mc.isActive = true")
    List<MemberClub> findMemberRelationInClub(@Param("club") Club club);

    // 특정 클럽에 속한 멤버 정보
    @Query(value = "select mc.compositeMemberClub.member from matching.member_club mc "
        + "where mc.compositeMemberClub.club = :club and mc.isActive = true")
    List<Member> findMemberInClub(@Param("club") Club club);

    // 해당 멤버가 속한 클럽 리스트
    @Query(value = "select mc.compositeMemberClub.club from matching.member_club mc "
        + "where mc.compositeMemberClub.member = :member and mc.isActive = true")
    List<Club> findClubByMember(@Param("member") Member member);

}