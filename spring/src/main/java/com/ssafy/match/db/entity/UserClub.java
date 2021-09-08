package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserClub;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.user_club")
public class UserClub {

    @EmbeddedId
    private CompositeUserClub compositeUserClub;

    private boolean is_active;
    private LocalDateTime register_date;
    private boolean authority;

    public UserClub() {
    }

    public UserClub(CompositeUserClub compositeUserClub, boolean is_active,
        LocalDateTime register_date, boolean authority) {
        this.compositeUserClub = compositeUserClub;
        this.is_active = is_active;
        this.register_date = register_date;
        this.authority = authority;
    }
}