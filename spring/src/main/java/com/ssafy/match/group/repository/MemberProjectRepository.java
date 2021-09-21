package com.ssafy.match.group.repository;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.embedded.CompositeMemberProject;
import com.ssafy.match.group.entity.MemberProject;
import com.ssafy.match.group.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberProjectRepository extends JpaRepository<MemberProject, CompositeMemberProject> {

    @Query(value = "select mp from matching.member_project mp where mp.compositeMemberProject.project = :project and mp.isActive = true")
    List<MemberProject> findMemberWithProject(@Param("project") Project project);

    @Query(value = "select mp from matching.member_project mp where mp.compositeMemberProject.project = :project and mp.compositeMemberProject.member = :member")
    MemberProject findMemberProject(@Param("project") Project project, @Param("member") Member member);
}