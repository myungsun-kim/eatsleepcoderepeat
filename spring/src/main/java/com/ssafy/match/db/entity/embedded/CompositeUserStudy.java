package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Club;
import com.ssafy.match.db.entity.Study;
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
public class CompositeUserStudy implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study_id;

    public CompositeUserStudy() {
    }

    public CompositeUserStudy(User user_id, Study study_id) {
        this.user_id = user_id;
        this.study_id = study_id;
    }
}
