package com.ssafy.match.group.repository.study;

import com.ssafy.match.group.entity.study.StudyTechstack;
import com.ssafy.match.group.entity.study.CompositeStudyTechstack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyTechstackRepository extends
    JpaRepository<StudyTechstack, CompositeStudyTechstack> {

}