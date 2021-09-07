package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.ProjectTechstack;
import com.ssafy.match.db.entity.embedded.CompositeProjectTechstack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTechstackRepository extends
    JpaRepository<ProjectTechstack, CompositeProjectTechstack> {

}