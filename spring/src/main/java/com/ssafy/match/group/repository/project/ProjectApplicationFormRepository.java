package com.ssafy.match.group.repository.project;

import com.ssafy.match.group.entity.project.CompositeMemberProject;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.entity.project.ProjectApplicationForm;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectApplicationFormRepository extends
    JpaRepository<ProjectApplicationForm, CompositeMemberProject> {

    @Query("select p from ProjectApplicationForm p where p.compositeMemberProject.project = :project")
    List<ProjectApplicationForm> formByProjectId(@Param("project") Project project);

    @Query("select p from ProjectApplicationForm p where p.compositeMemberProject = :cmp")
    Optional<ProjectApplicationForm> oneFormById(@Param("cmp") CompositeMemberProject cmp);
}