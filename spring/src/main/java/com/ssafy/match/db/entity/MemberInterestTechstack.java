package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.member_interest_techstack")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInterestTechstack {

    @EmbeddedId
    private CompositeMemberTechstack compositeMemberTechstack;

    public MemberInterestTechstack(
        CompositeMemberTechstack compositeMemberTechstack) {
        this.compositeMemberTechstack = compositeMemberTechstack;
    }
}