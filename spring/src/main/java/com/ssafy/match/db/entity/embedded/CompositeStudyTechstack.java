package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Project;
import com.ssafy.match.db.entity.Study;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.entity.User;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CompositeStudyTechstack implements Serializable {

    @ManyToOne
    @JoinColumn(name = "techstack_id")
    private Techstack techstack_id;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study_id;

    public CompositeStudyTechstack() {
    }

    public CompositeStudyTechstack(Techstack techstack_id, Study study_id) {
        this.techstack_id = techstack_id;
        this.study_id = study_id;
    }
}