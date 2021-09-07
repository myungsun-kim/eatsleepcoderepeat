package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}