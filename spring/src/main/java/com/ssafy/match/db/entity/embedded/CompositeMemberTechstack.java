package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.entity.Member;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;

@Getter
@Setter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompositeMemberTechstack implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "techstack_id")
    private Techstack teckstack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public CompositeMemberTechstack(Techstack teckstack, Member member) {
        this.teckstack = teckstack;
        this.member = member;
    }
}