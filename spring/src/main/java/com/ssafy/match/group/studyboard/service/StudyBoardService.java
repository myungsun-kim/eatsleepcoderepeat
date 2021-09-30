package com.ssafy.match.group.studyboard.service;

import com.ssafy.match.group.entity.study.Study;
import com.ssafy.match.group.repository.study.StudyRepository;
import com.ssafy.match.group.studyboard.dto.StudyBoardCreateRequestDto;
import com.ssafy.match.group.studyboard.entity.StudyBoard;
import com.ssafy.match.group.studyboard.repository.StudyBoardRepository;
import com.ssafy.match.member.entity.Member;
import com.ssafy.match.member.repository.MemberRepository;
import com.ssafy.match.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyBoardService {
    private final MemberRepository memberRepository;
    private final StudyBoardRepository studyBoardRepository;
    private final StudyRepository studyRepository;

    @Transactional
    public Long create(StudyBoardCreateRequestDto studyBoardCreateRequestDto) throws Exception {
        if (!studyRepository.existsById(studyBoardCreateRequestDto.getStudyId())) {
            throw new RuntimeException("존재하지 않는 study입니다.");
        }
        Study study = studyRepository.getById(studyBoardCreateRequestDto.getStudyId());
        System.out.println("######fir step");
        System.out.println(study.getId());
        StudyBoard studyBoard = studyBoardCreateRequestDto.toStudyBoard(study);
        System.out.println("######sec step");
        System.out.println(studyBoard.getName());
        StudyBoard ret = studyBoardRepository.save(studyBoard);
        System.out.println("######third step");
        System.out.println(ret);



        return ret.getId();
    }

    public Member findMember(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NullPointerException("회원 정보가 없습니다."));
        if (!member.getIs_active()) {
            throw new Exception("삭제된 멤버입니다.");
        }
        return member;
    }
}
