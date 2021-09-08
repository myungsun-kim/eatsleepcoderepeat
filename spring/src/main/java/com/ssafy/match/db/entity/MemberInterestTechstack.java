package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserTechstack;
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
    private CompositeUserTechstack compositeUserTechstack;

    public MemberInterestTechstack(
        CompositeUserTechstack compositeUserTechstack) {
        this.compositeUserTechstack = compositeUserTechstack;
    }
}