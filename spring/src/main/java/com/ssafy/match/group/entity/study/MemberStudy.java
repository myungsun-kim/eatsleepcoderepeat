package com.ssafy.match.group.entity.study;

import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.member_study")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberStudy {

    @EmbeddedId
    private CompositeMemberStudy compositeMemberStudy;

    private boolean is_active;
    private LocalDateTime register_date;
    private boolean authority;

    public MemberStudy(CompositeMemberStudy compositeMemberStudy, boolean is_active,
        LocalDateTime register_date, boolean authority) {
        this.compositeMemberStudy = compositeMemberStudy;
        this.is_active = is_active;
        this.register_date = register_date;
        this.authority = authority;
    }
}