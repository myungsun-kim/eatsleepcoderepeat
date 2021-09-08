package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.entity.User;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CompositeUserTechstack implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "techstack_id")
    private Techstack teckstack_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    public CompositeUserTechstack() {
    }

    public CompositeUserTechstack(Techstack teckstack_id, User user_id) {
        this.teckstack_id = teckstack_id;
        this.user_id = user_id;
    }
}