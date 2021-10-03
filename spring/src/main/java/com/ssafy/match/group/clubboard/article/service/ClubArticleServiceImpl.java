package com.ssafy.match.group.clubboard.article.service;

import com.ssafy.match.group.clubboard.article.dto.ClubArticleCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ClubArticleServiceImpl {

    @Transactional
    public Long createAriticle(ClubArticleCreateRequestDto dto){
        return 1L;
    }
}
