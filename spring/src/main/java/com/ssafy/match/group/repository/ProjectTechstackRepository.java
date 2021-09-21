package com.ssafy.match.group.repository;

import com.ssafy.match.group.entity.Project;
import com.ssafy.match.group.entity.ProjectTechstack;
import com.ssafy.match.db.entity.embedded.CompositeProjectTechstack;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectTechstackRepository extends JpaRepository<ProjectTechstack, CompositeProjectTechstack> {
    // 현재 프로젝트의 기술 스택 조회
    @Query("select pt from matching.project_techstack pt where pt.compositeProjectTechstack.project = :project and pt.isActive = true")
    List<ProjectTechstack> findByProjectTechstack(@Param("project") Project project);
}