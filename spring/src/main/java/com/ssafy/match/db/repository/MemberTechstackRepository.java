package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.MemberExperiencedTechstack;
import com.ssafy.match.db.entity.MemberTechstack;
import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberTechstackRepository extends JpaRepository<MemberTechstack, CompositeMemberTechstack> {

}