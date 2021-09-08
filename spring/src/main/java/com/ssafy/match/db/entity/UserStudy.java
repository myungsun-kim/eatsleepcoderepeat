package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserStudy;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.user_study")
public class UserStudy {

    @EmbeddedId
    private CompositeUserStudy compositeUserStudy;

    private boolean is_active;
    private LocalDateTime register_date;
    private boolean authority;

    public UserStudy() {
    }

    public UserStudy(CompositeUserStudy compositeUserStudy, boolean is_active,
        LocalDateTime register_date, boolean authority) {
        this.compositeUserStudy = compositeUserStudy;
        this.is_active = is_active;
        this.register_date = register_date;
        this.authority = authority;
    }
}