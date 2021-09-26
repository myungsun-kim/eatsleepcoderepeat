package com.ssafy.match.group.repository.study;

import com.ssafy.match.group.entity.study.CompositeMemberStudy;
import com.ssafy.match.group.entity.study.StudyApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyApplicationFormRepository extends
    JpaRepository<StudyApplicationForm, CompositeMemberStudy> {

}