package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserStudy;
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
    private CompositeUserStudy compositeUserStudy;

    private boolean is_active;
    private LocalDateTime register_date;
    private boolean authority;

    public MemberStudy(CompositeUserStudy compositeUserStudy, boolean is_active,
        LocalDateTime register_date, boolean authority) {
        this.compositeUserStudy = compositeUserStudy;
        this.is_active = is_active;
        this.register_date = register_date;
        this.authority = authority;
    }
}