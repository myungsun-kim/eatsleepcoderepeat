//package com.ssafy.match.db.repository;
//
////import com.ssafy.match.db.entity.Club;
//import com.ssafy.match.db.entity.MemberClub;
//import com.ssafy.match.db.entity.Position;
////import com.ssafy.match.db.entity.Project;
////import com.ssafy.match.db.entity.Study;
//import com.ssafy.match.db.entity.Member;
//import com.ssafy.match.db.entity.embedded.CompositeMemberClub;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    ClubRepository clubRepository;
//    @Autowired
//    StudyRepository studyRepository;
//    @Autowired
//    ProjectRepository projectRepository;
//    @Autowired
//    MemberClubRepository memberClubRepository;
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    public void userCRUD(){
//        Member member = new Member(LocalDateTime.now(), "qjawlsqjacks@naver.com", "박범진", "1234",
//            "BJP", "01028732329", "test", "coverpic?", "부천", false, "fsda");
//        Member member2 = new Member(LocalDateTime.now(), "qjawlsqjacks@naver.com", "박범찬", "1234",
//            "BJP", "01028732329", "test", "coverpic?", "부천", false, "fsda");
//
//        Club club = new Club("헬스", LocalDateTime.now(), "냠", 0, 7, 0, "afsd", "sfd", false);
////        Project project = new Project("프로젝트", LocalDateTime.now(), "FSda", 0, 7, 0, "fsda", "sfd", club, true, true);
////        Study study = new Study("스터디", LocalDateTime.now(), "FSda", 0, 7, 0, "fsda", null, club, true, true);
//
//        memberRepository.save(member);
//        memberRepository.save(member2);
//        clubRepository.save(club);
//
//
//        CompositeMemberClub compositeMemberClub = new CompositeMemberClub(member, club);
//        CompositeMemberClub compositeMemberClub1 = new CompositeMemberClub(member2, club);
//        MemberClub memberClub = new MemberClub(compositeMemberClub, false, LocalDateTime.now(), false);
//        MemberClub memberClub2 = new MemberClub(compositeMemberClub1, false, LocalDateTime.now(), false);
//
//        memberClubRepository.save(memberClub);
//        memberClubRepository.save(memberClub2);
//
////        List<Member> list = memberClubRepository.findby
////        projectRepository.save(project);
////        studyRepository.save(study);
//
//    }
//}