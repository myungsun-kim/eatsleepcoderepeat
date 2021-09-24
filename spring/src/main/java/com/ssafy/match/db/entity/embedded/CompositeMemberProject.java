package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.group.entity.Project;
import com.ssafy.match.db.entity.Member;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompositeMemberProject implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public CompositeMemberProject(Member member, Project project) {
        this.member = member;
        this.project = project;
    }
}