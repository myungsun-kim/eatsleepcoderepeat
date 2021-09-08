package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.UserClub;
import com.ssafy.match.db.entity.embedded.CompositeUserClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClubRepository extends JpaRepository<UserClub, CompositeUserClub> {

}