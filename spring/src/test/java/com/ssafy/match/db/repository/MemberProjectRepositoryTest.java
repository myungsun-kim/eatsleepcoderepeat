package com.ssafy.match.db.repository;

import com.ssafy.match.group.repository.ProjectRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberProjectRepositoryTest {

    @Autowired
    MemberProjectRepository memberProjectRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProjectRepository projectRepository;

//    @Test
//    @Transactional
//    @Rollback(value = false)
//    public void findMember(){
//
//        Member member = new Member(LocalDateTime.now(), "qjawlsqjacks@naver.com", "박범진", "1234",
//            "BJP", "01028732329", "test", null, "부천", false, null);
//        Member member2 = new Member(LocalDateTime.now(), "qjawlsqjacks@naver.com", "박범찬", "1234",
//            "BJP", "01028732329", "test", null, "부천", false, null);
//
//
//        Project project = Project.builder()
//            .name("프로젝트")
//            .member(member)
//            .createDate(LocalDateTime.now())
//            .modifyDate(LocalDateTime.now())
//            .schedule("매주 화 6시")
//            .period(7)
//            .bio("매칭 프로젝트")
//            .developerCount(0)
//            .developerMaxCount(3)
//            .plannerCount(0)
//            .plannerMaxCount(3)
//            .designerCount(0)
//            .designerMaxCount(3)
//            .city(City.구미)
//            .status(Status.모집중)
//            .isActive(true)
//            .isPublic(true)
//            .isParticipate(true)
//            .build();
//
//        CompositeMemberProject compositeMemberProject = new CompositeMemberProject(member, project);
//        CompositeMemberProject compositeMemberProject2 = new CompositeMemberProject(member2, project);
//
//        MemberProject memberProject = MemberProject.builder()
//            .compositeMemberProject(compositeMemberProject)
//            .is_active(true)
//            .register_date(LocalDateTime.now())
//            .authority(false)
//            .build();
//
//        MemberProject memberProject2 = MemberProject.builder()
//            .compositeMemberProject(compositeMemberProject2)
//            .is_active(true)
//            .register_date(LocalDateTime.now())
//            .authority(false)
//            .build();
//        memberRepository.save(member);
//        memberRepository.save(member2);
//        projectRepository.save(project);
//        memberProjectRepository.save(memberProject);
//        memberProjectRepository.save(memberProject2);
//
//        List<Member> list = memberProjectRepository.findMemberWithProject(1L);
//
//        for (Member mem : list) {
//            System.out.println(mem.getId() + " ");
//        }
//    }
}