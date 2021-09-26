package com.ssafy.match.service;

import com.ssafy.match.controller.dto.*;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.MemberSns;
import com.ssafy.match.db.repository.*;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.repository.project.MemberProjectRepository;
import com.ssafy.match.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final MemberSnsRepository memberSnsRepository;
    private final PasswordEncoder passwordEncoder;
//    private final MemberService memberService;
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
        List<MemberSns> snsList = memberSnsRepository.findAllByMember(member);
        memberInfoDto.setMyProjectList(myProjectList);
        memberInfoDto.setMyClubList(myClubList);
        memberInfoDto.setExpTechList(expTechList);
        memberInfoDto.setBeginTechList(begTechList);
        memberInfoDto.setSnsList(snsList);
        return memberInfoDto;
    }

    //    @Transactional(readOnly = true)
//    public MemberInfoDto getMyPage() {
//        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
//                .map(MemberInfoDto::new)
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
//    }

    @Transactional
    public MemberUpdateResponseDto updateMyInfo(MemberUpdateRequestDto memberUpdateRequestDto) {
        updateSns(memberUpdateRequestDto.getSnsHashMap());
        updateMember(memberUpdateRequestDto.getEmail(), memberUpdateRequestDto.getName(), memberUpdateRequestDto.getPassword(), memberUpdateRequestDto.getNickname(), memberUpdateRequestDto.getTel(), memberUpdateRequestDto.getBio(), memberUpdateRequestDto.getCity(), memberUpdateRequestDto.getPosition(), memberUpdateRequestDto.getPortfolio_uri());
        return MemberUpdateResponseDto.of(SecurityUtil.getCurrentMemberId());
    }



//    public MemberResponseDto modifyMyInfo(@RequestBody @Valid MemberModifyRequestDto dto) {
//        Long currentMemberId = SecurityUtil.getCurrentMemberId();
//
//        Member member = memberRepository.getById(currentMemberId);
//
//        member.setNickname(dto.getNickname());
//        member.setBio(dto.getBio());
//        member.setCity(dto.getCity());
//
//        setDBFile(member, dto.getUuid());
//
//        return MemberResponseDto.of(memberRepository.save(member));
//    }

    public void setDBFile(Member member, String uuid){
        if(uuid == null) {
            member.setDbFile(null);
            return;
        }

        DBFile dbFile = dbFileRepository.getById(uuid);
        member.setDbFile(dbFile);
    }

    @Transactional
    public void updateMember(String email, String name, String password, String nickname, String tel, String bio, String city, String position, String portfolio_uri) {
        Member member = memberRepository.getById(SecurityUtil.getCurrentMemberId());

        if (memberRepository.existsByNickname(nickname)) {
        } else {
            member.setNickname(nickname);
        }
        member.setPassword(passwordEncoder.encode(password));
        member.setTel(tel);
        member.setName(name);
        member.setEmail(email);
        member.setBio(bio);
        member.setCity(city);
        member.setPosition(position);
        member.setPortfolio_uri(portfolio_uri);
    }

    @Transactional
    public void updateSns(HashMap<String, String> snsList) {
        Member member = memberRepository.getById(SecurityUtil.getCurrentMemberId());
        if (snsList != null && !snsList.isEmpty()) {
            snsList.forEach((strKey, strValue) -> {
                Optional<MemberSns> memberSns = memberSnsRepository.findByMemberAndSnsName(member, strKey);
                if (memberSns.isEmpty()) {
                    MemberSns innerMemberSns = MemberSns.builder()
                            .member(member)
                            .snsAccount(strValue)
                            .snsName(strKey)
                            .build();
                    memberSnsRepository.save(innerMemberSns);
                } else {
                    MemberSns innerMemberSns = memberSns.get();
                    innerMemberSns.setSnsAccount(strValue);
                    innerMemberSns.setSnsName(strKey);
                }
            });
        }
    }
}
