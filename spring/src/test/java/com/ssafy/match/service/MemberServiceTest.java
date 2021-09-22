package com.ssafy.match.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.file.entity.DBFile;
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
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

//    @Test
//    @Transactional
//    @Rollback(value = false)
//    public void modifyMember(){
//        byte[] a = new byte[]{1};
//        DBFile dbFile = new DBFile("df", "pdf", a);
//        Member member = new Member(LocalDateTime.now(), "qjawlsqjacks@naver.com", "박범진", "1234",
//            "BJP", "01028732329", "test", dbFile, "부천", false, null);
//
//        memberRepository.save(member);
//        member = memberRepository.getById(1L);
//
//        member.setNickname("적당히해");
//        member.setBio("멤버 수정 짜증");
//        member.setCity("광주");
//
//        memberService.setDBFile(member, null);
//        memberRepository.save(member);
//    }
}