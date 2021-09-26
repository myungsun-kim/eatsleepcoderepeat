package com.ssafy.match.group.dto.study.request;

import com.ssafy.match.file.entity.DBFile;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyApplicationRequestDto {

    private String nickname;

    private String city;

    private String git;

    private String twitter;

    private String facebook;

    private String backjoon;

    private String bio;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_pic")
    private DBFile dbFile;

    @Builder
    public StudyApplicationRequestDto(String nickname, String city, String git, String twitter,
        String facebook, String backjoon, String bio, LocalDateTime createDate, DBFile dbFile) {
        this.nickname = nickname;
        this.city = city;
        this.git = git;
        this.twitter = twitter;
        this.facebook = facebook;
        this.backjoon = backjoon;
        this.bio = bio;
        this.createDate = createDate;
        this.dbFile = dbFile;
    }
}