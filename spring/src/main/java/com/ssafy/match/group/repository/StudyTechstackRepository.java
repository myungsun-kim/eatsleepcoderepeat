package com.ssafy.match.group.repository;

import com.ssafy.match.group.entity.StudyTechstack;
import com.ssafy.match.db.entity.embedded.CompositeStudyTechstack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyTechstackRepository extends
    JpaRepository<StudyTechstack, CompositeStudyTechstack> {

}