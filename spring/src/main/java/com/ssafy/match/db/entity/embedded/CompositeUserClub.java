package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Club;
import com.ssafy.match.db.entity.User;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CompositeUserClub implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club_id;

    public CompositeUserClub() {
    }

    public CompositeUserClub(User user_id, Club club_id) {
        this.user_id = user_id;
        this.club_id = club_id;
    }
}
