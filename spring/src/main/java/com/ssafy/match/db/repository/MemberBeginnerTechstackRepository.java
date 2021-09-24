package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.MemberExperiencedTechstack;
import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBeginnerTechstackRepository extends
        JpaRepository<MemberExperiencedTechstack, CompositeMemberTechstack> {
}
