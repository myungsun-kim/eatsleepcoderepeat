package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.UserProject;
import com.ssafy.match.db.entity.embedded.CompositeUserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProject, CompositeUserProject> {

}