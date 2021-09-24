package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "matching.member_beginner_techstack")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberBeginnerTechstack {

    @EmbeddedId
    private CompositeMemberTechstack compositeMemberTechstack;

    public MemberBeginnerTechstack(
            CompositeMemberTechstack compositeMemberTechstack) {
        this.compositeMemberTechstack = compositeMemberTechstack;
    }
}
