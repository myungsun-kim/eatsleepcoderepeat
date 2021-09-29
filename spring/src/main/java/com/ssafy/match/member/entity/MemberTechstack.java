package com.ssafy.match.member.entity;

import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.*;

@Getter
//@Setter
@Entity(name = "matching.member_techstack")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTechstack {

    @EmbeddedId
    private CompositeMemberTechstack compositeMemberTechstack;

    @Builder
    public MemberTechstack(
        CompositeMemberTechstack compositeMemberTechstack) {
        this.compositeMemberTechstack = compositeMemberTechstack;
    }
}