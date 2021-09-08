package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.UserStudy;
import com.ssafy.match.db.entity.embedded.CompositeUserStudy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyRepository extends JpaRepository<UserStudy, CompositeUserStudy> {

}