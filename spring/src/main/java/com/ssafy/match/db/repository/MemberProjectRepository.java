package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.MemberProject;
import com.ssafy.match.db.entity.embedded.CompositeMemberProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProjectRepository extends JpaRepository<MemberProject, CompositeMemberProject> {

}