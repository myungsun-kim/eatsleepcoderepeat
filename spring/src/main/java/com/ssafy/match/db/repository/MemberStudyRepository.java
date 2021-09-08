package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.MemberStudy;
import com.ssafy.match.db.entity.embedded.CompositeMemberStudy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStudyRepository extends JpaRepository<MemberStudy, CompositeMemberStudy> {

}