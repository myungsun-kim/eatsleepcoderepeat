package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.MemberClub;
import com.ssafy.match.db.entity.embedded.CompositeUserClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberClubRepository extends JpaRepository<MemberClub, CompositeUserClub> {

}