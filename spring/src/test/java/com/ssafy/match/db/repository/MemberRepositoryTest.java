package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.Club;
import com.ssafy.match.db.entity.Position;
import com.ssafy.match.db.entity.Project;
import com.ssafy.match.db.entity.Study;
import com.ssafy.match.db.entity.Member;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    PositionRepository positionRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void userCRUD(){
//        Member member = new Member(LocalDateTime.now(), "qjawlsqjacks@naver.com", "박범진", "1234", "BJP", "01028732329", "test", "coverpic?", "부천", false, "fsda");
//        Position position = new Position(member, "개발자");
//        Club club = new Club("헬스", LocalDateTime.now(), "냠", 0, 7, 0, "afsd", "sfd", false  );
//        Project project = new Project("프로젝트", LocalDateTime.now(), "FSda", 0, 7, 0, "fsda", "sfd", club, true, true);
//        Study study = new Study("스터디", LocalDateTime.now(), "FSda", 0, 7, 0, "fsda", null, club, true, true);


//        memberRepository.save(member);
//        positionRepository.save(position);
//        clubRepository.save(club);
//        projectRepository.save(project);
//        studyRepository.save(study);

    }
}