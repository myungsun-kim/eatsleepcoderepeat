package com.ssafy.match.service;

import com.ssafy.match.controller.dto.MemberInfoDto;
import com.ssafy.match.controller.dto.MemberModifyRequestDto;
import com.ssafy.match.controller.dto.MemberResponseDto;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final DBFileRepository dbFileRepository;

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
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberInfoDto::new)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }
//    @Transactional(readOnly = true)
//    public MemberInfoDto getMyPage() {
//        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
//                .map(MemberInfoDto::new)
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
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
