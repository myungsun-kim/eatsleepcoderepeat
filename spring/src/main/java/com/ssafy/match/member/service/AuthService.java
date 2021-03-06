package com.ssafy.match.member.service;

import com.ssafy.match.member.dto.*;
import com.ssafy.match.db.entity.*;
import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import com.ssafy.match.db.repository.*;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.jwt.TokenProvider;
import com.ssafy.match.member.entity.Member;
import com.ssafy.match.member.entity.MemberBeginnerTechstack;
import com.ssafy.match.member.entity.MemberExperiencedTechstack;
import com.ssafy.match.member.entity.Position;
import com.ssafy.match.member.repository.MemberBeginnerTechstackRepository;
import com.ssafy.match.member.repository.MemberExperiencedTechstackRepository;
import com.ssafy.match.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberExperiencedTechstackRepository memberExperiencedTechstackRepository;
    private final TechstackRepository techstackRepository;
    private final MemberBeginnerTechstackRepository memberBeginnerTechstackRepository;
    private final PositionRepository positionRepository;
    private final DBFileRepository dbFileRepository;

    @Transactional(readOnly = true)
    public Boolean checkEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Transactional(readOnly = true)
    public Boolean checkNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) throws Exception {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            throw new RuntimeException("?????? ???????????? ?????? ???????????????");
        }
        Member member = memberRequestDto.toMember(passwordEncoder);
        Member ret = memberRepository.save(member);
//        DBFile coverPic = setCoverPic(memberRequestDto.getCover_pic());
//        DBFile portfolio = setPortfolioUuid(memberRequestDto.getPortfolio_uuid());

        if (memberRequestDto.getExpTechList() != null){
            for (String techExp : memberRequestDto.getExpTechList()) {
                Techstack techstackExp = techstackRepository.findByName(techExp)
                        .orElseThrow(() -> new NullPointerException("?????? ?????? ????????? ????????????."));
                CompositeMemberTechstack compositeMemberTechstackExp = CompositeMemberTechstack
                        .builder()
                        .member(ret)
                        .techstack(techstackExp)
                        .build();
                MemberExperiencedTechstack memberExperiencedTechstack = MemberExperiencedTechstack.builder().compositeMemberTechstack(compositeMemberTechstackExp).build();

                memberExperiencedTechstackRepository.save(memberExperiencedTechstack);
                //            MemberExperiencedTechstack memberExperiencedTechstack = memberRequestDto.toMemberExperiencedTechstack(member, techstack);
                //            memberExperiencedTechstackRepository.save(memberExperiencedTechstack);
            }
        }
        if (memberRequestDto.getBeginTechList() != null){
            for (String techBegin : memberRequestDto.getBeginTechList()) {
                Techstack techstackBegin = techstackRepository.findByName(techBegin)
                        .orElseThrow(() -> new NullPointerException("?????? ?????? ????????? ????????????."));
                CompositeMemberTechstack compositeMemberTechstackBegin = CompositeMemberTechstack
                        .builder()
                        .member(ret)
                        .techstack(techstackBegin)
                        .build();
                MemberBeginnerTechstack memberBeginnerTechstack = MemberBeginnerTechstack.builder().compositeMemberTechstack(compositeMemberTechstackBegin).build();
                memberBeginnerTechstackRepository.save(memberBeginnerTechstack);
            }
        }
        if (memberRequestDto.getDpositionList() != null) {
            for (String dposition : memberRequestDto.getDpositionList()) {
                Position innerDposition = Position
                        .builder()
                        .member(ret)
                        .name(dposition)
                        .build();
                positionRepository.save(innerDposition);
            }
        }
//        member.setCover_pic(coverPic);
//        member.setPortfolio(portfolio);
        return MemberResponseDto.of(ret);
    }

    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto) throws Exception{
        // 1. Login ID/PW ??? ???????????? AuthenticationToken ??????
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();
        // 2. ????????? ?????? (????????? ???????????? ??????) ??? ??????????????? ??????
        //    authenticate ???????????? ????????? ??? ??? CustomUserDetailsService ?????? ???????????? loadUserByUsername ???????????? ?????????
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //???????????? ??????
        Member member = memberRepository.getById(Long.parseLong(authentication.getName()));
        if (!member.getIs_active()) {
            throw new Exception("????????? ???????????????!");
        }
        // 3. ?????? ????????? ???????????? JWT ?????? ??????
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken ??????
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. ?????? ??????
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token ??????
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token ??? ???????????? ????????????.");
        }

        // 2. Access Token ?????? Member ID ????????????
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. ??????????????? Member ID ??? ???????????? Refresh Token ??? ?????????
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("???????????? ??? ??????????????????."));

        // 4. Refresh Token ??????????????? ??????
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("????????? ?????? ????????? ???????????? ????????????.");
        }

        // 5. ????????? ?????? ??????
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. ????????? ?????? ????????????
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // ?????? ??????
        return tokenDto;
    }

    public DBFile setCoverPic(String uuid) {
//        if(uuid == null) {
//            member.setCover_pic(null);
//            return;
//        }
        DBFile dbFile = dbFileRepository.getById(uuid);
        return dbFile;
    }

    @Transactional
    public DBFile setPortfolioUuid(String uuid) {
//        if(uuid == null) {
//            member.setPortfolio(null);
//            return;
//        }
        DBFile dbFile = dbFileRepository.getById(uuid);
//        member.setPortfolio(dbFile);
        return dbFile;
    }
}