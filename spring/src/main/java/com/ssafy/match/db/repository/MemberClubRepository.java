package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.MemberClub;
import com.ssafy.match.db.entity.embedded.CompositeMemberClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberClubRepository extends JpaRepository<MemberClub, CompositeMemberClub> {

}