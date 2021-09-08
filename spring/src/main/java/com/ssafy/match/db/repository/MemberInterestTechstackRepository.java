package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.MemberInterestTechstack;
import com.ssafy.match.db.entity.embedded.CompositeUserTechstack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInterestTechstackRepository extends
    JpaRepository<MemberInterestTechstack, CompositeUserTechstack> {

}