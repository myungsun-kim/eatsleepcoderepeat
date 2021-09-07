package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.UserTechstack;
import com.ssafy.match.db.entity.embedded.CompositeUserTechstack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTechstackRepository extends
    JpaRepository<UserTechstack, CompositeUserTechstack> {

}