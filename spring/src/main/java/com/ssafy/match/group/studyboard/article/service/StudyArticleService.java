package com.ssafy.match.group.studyboard.article.service;


import com.ssafy.match.group.studyboard.article.dto.StudyArticleCreateRequestDto;
import com.ssafy.match.group.studyboard.article.dto.StudyArticleInfoDto;
import com.ssafy.match.group.studyboard.article.entity.StudyArticle;
import com.ssafy.match.group.studyboard.article.repository.StudyArticleRepository;
import com.ssafy.match.group.studyboard.board.entity.StudyBoard;
import com.ssafy.match.group.studyboard.board.repository.StudyBoardRepository;
import com.ssafy.match.member.entity.Member;
import com.ssafy.match.member.repository.MemberRepository;
import com.ssafy.match.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyArticleService {
    private final StudyBoardRepository studyBoardRepository;
    private final StudyArticleRepository studyArticleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createArticle(StudyArticleCreateRequestDto studyArticleCreateRequestDto) throws Exception {
        if (!studyBoardRepository.existsById(studyArticleCreateRequestDto.getBoardId())) {
            throw new RuntimeException("존재하지 않는 게시판 입니다!");
        }
        StudyBoard studyBoard = studyBoardRepository.getById(studyArticleCreateRequestDto.getBoardId());
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new NullPointerException("존재하지 않는 사용자 입니다."));
        StudyArticle studyArticle = studyArticleCreateRequestDto.toStudyBoard(member, studyBoard);
        StudyArticle ret = studyArticleRepository.save(studyArticle);
//        StudyArticle ret = studyArticleRepository.findAllByStudyBoard(studyBoard);
        return ret.getId();
    }

    @Transactional(readOnly = true)
    public List<StudyArticleInfoDto> getStudyArticles(Integer boardId) throws Exception {
        if (!studyBoardRepository.existsById(boardId)) {
            throw new RuntimeException("존재하지 않는 게시판입니다");
        }
        StudyBoard studyBoard = studyBoardRepository.getById(boardId);
        System.out.println(studyArticleRepository.findAllByStudyBoard(studyBoard));
        List<StudyArticleInfoDto> studyArticleInfoDtos = studyArticleRepository.findAllByStudyBoard(studyBoard).stream()
                .map(StudyArticleInfoDto::of)
                .collect(Collectors.toList());
        return studyArticleInfoDtos;
    }
}
