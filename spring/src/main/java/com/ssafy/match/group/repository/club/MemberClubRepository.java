package com.ssafy.match.group.repository.club;

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
    // 해당 멤버가 속한 클럽 리스트
    @Query(value = "select mc.compositeMemberClub.club from matching.member_club mc "
        + "where mc.compositeMemberClub.member = :member and mc.is_active = true")
    List<Club> findClubByMember(@Param("member") Member member);

    // 해당 멤버가 속한 클럽의 id, name 정보 리스트
    @Query(value = "select mc.compositeMemberClub.club.id, mc.compositeMemberClub.club.name from matching.member_club mc "
        + "where mc.compositeMemberClub.member = :member and mc.is_active = true")
    List<ClubDto> findClubIdNameByMember(@Param("member") Member member);
}