package com.ssafy.match.group.dto.study.response;

import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.group.entity.study.StudyApplicationForm;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 신청서 정보 조회
public class StudyFormInfoResponseDto {

    private Long studyId;
    private Long memberId;
    private String nickname;
    private String city;
    private String git;
    private String twitter;
    private String facebook;
    private String backjoon;
    private List<String> strong;
    private List<String> knowledgeable;
    private String bio;
    private DBFile dbFile;

    @Builder
    public StudyFormInfoResponseDto(StudyApplicationForm form, List<String> strong, List<String> knowledgeable){
        this.studyId = form.getCompositeMemberStudy().getStudy().getId();
        this.memberId = form.getCompositeMemberStudy().getMember().getId();
        this.nickname = form.getNickname();
        this.city = form.getCity().name();
        this.git = form.getGit();
        this.twitter = form.getTwitter();
        this.facebook = form.getFacebook();
        this.backjoon = form.getBackjoon();
        this.strong = strong;
        this.knowledgeable = knowledgeable;
        this.bio = form.getBio();
        this.dbFile = form.getDbFile();
    }
}
