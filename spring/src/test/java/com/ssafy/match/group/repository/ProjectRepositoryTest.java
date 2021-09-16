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

    @Test
    @Transactional
    @Rollback(value = false)
    public void projectCreate(){

        Member member = new Member(LocalDateTime.now(), "qjawlsqjacks@naver.com", "박범진", "1234",
            "BJP", "01028732329", "df", null, "부천", false, null);
        Member member2 = new Member(LocalDateTime.now(), "qjawlsqjacks@naver.com", "박범찬", "1234",
            "BJP", "01028732329", "test", null, "부천", false, null);

        Club club = new Club("헬스", member, LocalDateTime.now(), "냠", City.구미, 1,  6, true, true, null);

        Techstack techstack1 = new Techstack("JAVA");
        Techstack techstack2 = new Techstack("SPRING");

        Project project = Project.builder()
            .name("프로젝트")
            .member(member)
            .createDate(LocalDateTime.now())
            .modifyDate(LocalDateTime.now())
            .schedule("매주 화 6시")
            .period(7)
            .bio("매칭 프로젝트")
            .developerCount(0)
            .developerMaxCount(3)
            .plannerCount(0)
            .plannerMaxCount(3)
            .designerCount(0)
            .designerMaxCount(3)
            .city(City.구미)
            .status(Status.모집중)
            .isActive(true)
            .isPublic(true)
            .isParticipate(true)
            .build();

        projectServiceImpl.changeRole(project, "디자이너");

        memberRepository.save(member);
        memberRepository.save(member2);
        clubRepository.save(club);
        techstackRepository.save(techstack1);
        techstackRepository.save(techstack2);
        projectRepository.save(project);

    }

}