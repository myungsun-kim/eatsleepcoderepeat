package com.ssafy.match.group.repository.study;

import com.ssafy.match.group.entity.study.MemberStudy;
import com.ssafy.match.group.entity.study.CompositeMemberStudy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStudyRepository extends JpaRepository<MemberStudy, CompositeMemberStudy> {

}