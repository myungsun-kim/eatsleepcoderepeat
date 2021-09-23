package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeMemberTechstack;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

@Getter
@Setter
@Entity(name = "matching.member_experienced_techstack")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberExperiencedTechstack {

    @EmbeddedId
    private CompositeMemberTechstack compositeMemberTechstack;

    public MemberExperiencedTechstack(
        CompositeMemberTechstack compositeMemberTechstack) {
        this.compositeMemberTechstack = compositeMemberTechstack;
    }
}