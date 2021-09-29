package com.ssafy.match.group.dto.club.response;

import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.group.entity.club.ClubApplicationForm;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 신청서 정보 조회
public class ClubFormInfoResponseDto {

    private Long clubId;
    private Long memberId;
    private String nickname;
    private String city;
    private String git;
    private String twitter;
    private String facebook;
    private String backjoon;
    private List<String> experiencedTechstack;

    private List<String> beginnerTechstack;
    private String bio;
    private DBFile dbFile;

    @Builder
    public ClubFormInfoResponseDto(ClubApplicationForm form, List<String> experiencedTechstack, List<String> beginnerTechstack){
        this.clubId = form.getCompositeMemberClub().getClub().getId();
        this.memberId = form.getCompositeMemberClub().getMember().getId();
        this.nickname = form.getNickname();
        this.city = form.getCity().name();
        this.git = form.getGit();
        this.twitter = form.getTwitter();
        this.facebook = form.getFacebook();
        this.backjoon = form.getBackjoon();
        this.experiencedTechstack = experiencedTechstack;
        this.beginnerTechstack = beginnerTechstack;
        this.bio = form.getBio();
        this.dbFile = form.getDbFile();
    }
}
