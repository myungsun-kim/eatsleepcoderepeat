package com.ssafy.match.group.repository;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.group.entity.Club;
import com.ssafy.match.group.entity.Project;
import com.ssafy.match.group.service.ProjectServiceImpl;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProjectRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    TechstackRepository techstackRepository;
    @Autowired
    ProjectTechstackRepository  projectTechstackRepository;
    @Autowired
    ProjectServiceImpl projectServiceImpl;



}