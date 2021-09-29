package com.ssafy.match.group.dto.project.response;

import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.group.entity.project.ProjectApplicationForm;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 신청서 정보 조회
public class ProjectFormInfoResponseDto {

    private Long projectId;

    private Long memberId;
    private String nickname;

    private String city;

    private String role;

    private String position;

    private String git;

    private String twitter;

    private String facebook;

    private String backjoon;

    private List<String> strong;

    private List<String> knowledgeable;

    private String bio;

    private DBFile dbFile;

    @Builder
    public ProjectFormInfoResponseDto(ProjectApplicationForm form, List<String> strong, List<String> knowledgeable){
        this.projectId = form.getCompositeMemberProject().getProject().getId();
        this.memberId = form.getCompositeMemberProject().getMember().getId();
        this.nickname = form.getNickname();
        this.city = form.getCity().toString();
        this.role = form.getRole();
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