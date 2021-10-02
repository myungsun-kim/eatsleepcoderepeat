package com.ssafy.match.group.clubboard.article.service;


import com.ssafy.match.group.clubboard.article.dto.ClubArticleCreateRequestDto;
import com.ssafy.match.group.clubboard.article.dto.ClubArticleInfoDto;
import com.ssafy.match.group.clubboard.article.dto.ClubArticleListDto;
import com.ssafy.match.group.clubboard.article.dto.ClubArticleUpdateRequestDto;
import com.ssafy.match.group.clubboard.article.entity.ClubArticle;
import com.ssafy.match.group.clubboard.article.entity.ClubContent;
import com.ssafy.match.group.clubboard.article.repository.ClubArticleRepository;
import com.ssafy.match.group.clubboard.article.repository.ClubContentRepository;
import com.ssafy.match.group.clubboard.board.entity.ClubBoard;
import com.ssafy.match.group.clubboard.board.repository.ClubBoardRepository;
import com.ssafy.match.member.entity.Member;
import com.ssafy.match.member.repository.MemberRepository;
import com.ssafy.match.util.SecurityUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClubArticleService {
    private final ClubBoardRepository clubBoardRepository;
    private final ClubArticleRepository clubArticleRepository;
    private final ClubContentRepository clubContentRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public ClubArticleInfoDto getClubArticleDetail(Long articleId) {
        ClubArticleInfoDto clubArticleInfoDto = clubArticleRepository.findById(articleId).map(ClubArticleInfoDto::of).orElseThrow(() -> new NullPointerException("존재하지 않는 게시물입니다."));
        ClubArticle clubArticle = clubArticleRepository.getById(articleId);
        Optional<ClubContent> clubContent =clubContentRepository.getByClubArticle(clubArticle);
        if (clubContent.isEmpty()) {
            throw new RuntimeException("내용이 존재하지 않습니다");
        }
        String content = clubContent.get().getContent();
        clubArticleInfoDto.setContent(content);
        return clubArticleInfoDto;
    }

    @Transactional(readOnly = true)
    public List<ClubArticleListDto> getClubArticles(Integer boardId) throws Exception {
        if (!clubBoardRepository.existsById(boardId)) {
            throw new RuntimeException("존재하지 않는 게시판입니다");
        }
        ClubBoard clubBoard = clubBoardRepository.getById(boardId);
        List<ClubArticleListDto> clubArticleListDtos = clubArticleRepository.findAllByClubBoard(clubBoard).stream()
                .map(ClubArticleListDto::of)
                .collect(Collectors.toList());
        return clubArticleListDtos;
    }

    @Transactional
    public Long createArticle(Integer boardId, ClubArticleCreateRequestDto clubArticleCreateRequestDto) throws Exception {
        if (!clubBoardRepository.existsById(boardId)) {
            throw new RuntimeException("존재하지 않는 게시판 입니다!");
        }
        ClubBoard clubBoard = clubBoardRepository.getById(boardId);
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new NullPointerException("존재하지 않는 사용자 입니다."));
        ClubArticle clubArticle = clubArticleCreateRequestDto.toClubBoard(member, clubBoard);
        if (clubArticleCreateRequestDto.getContent() == null) {
            throw new RuntimeException("내용이 존재하지 않습니다!");
        }
        ClubArticle ret = clubArticleRepository.save(clubArticle);
        addContent(clubArticle, clubArticleCreateRequestDto.getContent());
        return ret.getId();
    }

    @Transactional
    public Long updateArticle(Long articleId, ClubArticleUpdateRequestDto clubArticleUpdateRequestDto) {
        if (!clubArticleRepository.existsById(articleId)) {
            throw new RuntimeException("존재하지 않는 게시물 입니다!");
        }
        ClubArticle clubArticle = clubArticleRepository.getById(articleId);
//        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new NullPointerException("존재하지 않는 사용자 입니다."));
        if (clubArticleUpdateRequestDto.getContent() == null) {
            throw new RuntimeException("내용이 존재하지 않습니다!");
        }
        Optional<ClubContent> clubContent = clubContentRepository.getByClubArticle(clubArticle);
        if (clubContent.isEmpty()) {
            throw new RuntimeException("내용이 존재하지 않습니다");
        }
        updateContent(clubArticle, clubArticleUpdateRequestDto.getContent(), clubContent.get());

        clubArticle.setModifiedDate(LocalDateTime.now());
        clubArticle.setTitle(clubArticleUpdateRequestDto.getTitle());
        return clubArticle.getId();
    }

    @Transactional
    public Boolean deleteArticle(Long articleId) {
        if (!clubArticleRepository.existsById(articleId)) {
            throw new RuntimeException("존재하지 않는 게시물 입니다!");
        }
        ClubArticle clubArticle = clubArticleRepository.getById(articleId);
        ClubContent clubContent = clubContentRepository.getByClubArticle(clubArticle).orElseThrow(()-> new NullPointerException("내용이 존재하지 않습니다."));
        clubContentRepository.delete(clubContent);
        clubArticleRepository.delete(clubArticle);
        return Boolean.TRUE;
    }

    @Transactional
    public void updateContent(ClubArticle clubArticle, String content, ClubContent clubContent) {
        clubContent.setContent(content);
        clubContent.setClubArticle(clubArticle);
    }

    @Transactional
    public void addContent(ClubArticle clubArticle, String content) {
        ClubContent clubContent = ClubContent.builder()
                .clubArticle(clubArticle)
                .content(content)
                .build();
        clubContentRepository.save(clubContent);
    }



}
