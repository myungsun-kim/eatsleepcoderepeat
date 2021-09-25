package com.ssafy.match.service;

import com.ssafy.match.controller.dto.MemberInfoDto;
import com.ssafy.match.controller.dto.MemberModifyRequestDto;
import com.ssafy.match.controller.dto.MemberResponseDto;
import com.ssafy.match.controller.dto.MemberUpdateDto;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.repository.MemberBeginnerTechstackRepository;
import com.ssafy.match.db.repository.MemberClubRepository;
import com.ssafy.match.db.repository.MemberExperiencedTechstackRepository;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.project.CompositeProjectTechstack;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.entity.project.ProjectTechstack;
import com.ssafy.match.group.repository.project.MemberProjectRepository;
import com.ssafy.match.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final DBFileRepository dbFileRepository;
    private final MemberExperiencedTechstackRepository memberExperiencedTechstackRepository;
    private final MemberBeginnerTechstackRepository memberBeginnerTechstackRepository;
    private final MemberClubRepository memberClubRepository;
    private final MemberProjectRepository memberProjectRepository;
//    @Transactional(readOnly = true)
//    public MemberResponseDto getMemberInfo(String email) {
//        return memberRepository.findByEmail(email)
//                .map(MemberResponseDto::of)
//                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
//    }
//
//    // 현재 SecurityContext 에 있는 유저 정보 가져오기
//    @Transactional(readOnly = true)
//    public MemberResponseDto getMyInfo() {
//        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
//                .map(MemberResponseDto::of)
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
//    }

    @Transactional(readOnly = true)
    public MemberInfoDto getMyPage() {
        MemberInfoDto memberInfoDto = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberInfoDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new NullPointerException("유저가 없습니다."));
        List<Club> myClubList = memberClubRepository.findClubByMember(member);
        List<Project> myProjectList = memberProjectRepository.projectInMember(member);
        List<String> expTechList = memberExperiencedTechstackRepository.findTechstackByMemberName(member);
        List<String> begTechList = memberBeginnerTechstackRepository.findTechstackByMemberName(member);
        memberInfoDto.setMyProjectList(myProjectList);
        memberInfoDto.setMyClubList(myClubList);
        memberInfoDto.setExpTechList(expTechList);
        memberInfoDto.setBeginTechList(begTechList);
        return memberInfoDto;
    }

    //    @Transactional(readOnly = true)
//    public MemberInfoDto getMyPage() {
//        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
//                .map(MemberInfoDto::new)
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
//    }

//    @Transactional
//    public MemberUpdateDto updateMyInfo(MemberUpdateDto memberUpdateDto) {
//        Long currentMemberId = SecurityUtil.getCurrentMemberId();
//        Member member = memberRepository.getById(currentMemberId);
//        if (memberRepository.existsByNickname(memberUpdateDto.getNickname())) {
//            throw new RuntimeException("이미 존재하는 닉네임입니다");
//        } else {
//            member.setNickname(memberUpdateDto.getNickname());
//        }
//        member.setCity();
//
//        return;
//    }

    public MemberResponseDto modifyMyInfo(MemberModifyRequestDto dto) {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();

        Member member = memberRepository.getById(currentMemberId);

        member.setNickname(dto.getNickname());
        member.setBio(dto.getBio());
        member.setCity(dto.getCity());

        setDBFile(member, dto.getUuid());

        return MemberResponseDto.of(memberRepository.save(member));
    }

    public void setDBFile(Member member, String uuid){
        if(uuid == null) {
            member.setDbFile(null);
            return;
        }

        DBFile dbFile = dbFileRepository.getById(uuid);
        member.setDbFile(dbFile);

    }

}
