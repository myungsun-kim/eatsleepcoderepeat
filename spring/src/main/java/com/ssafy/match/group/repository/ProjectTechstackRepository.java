package com.ssafy.match.group.repository;

import com.ssafy.match.group.entity.Project;
import com.ssafy.match.group.entity.ProjectTechstack;
import com.ssafy.match.db.entity.embedded.CompositeProjectTechstack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTechstackRepository extends JpaRepository<ProjectTechstack, CompositeProjectTechstack> {

    ProjectTechstack[] findByProject(Project project);
}