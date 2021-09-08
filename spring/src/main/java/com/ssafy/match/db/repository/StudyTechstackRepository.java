package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.StudyTechstack;
import com.ssafy.match.db.entity.embedded.CompositeStudyTechstack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyTechstackRepository extends
    JpaRepository<StudyTechstack, CompositeStudyTechstack> {

}