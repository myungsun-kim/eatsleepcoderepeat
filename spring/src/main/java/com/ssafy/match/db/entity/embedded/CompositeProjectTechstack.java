package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Project;
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
public class CompositeProjectTechstack implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "techstack_id")
    private Techstack techstack_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project_id;

    public CompositeProjectTechstack() {
    }

    public CompositeProjectTechstack(Techstack techstack_id, Project project_id) {
        this.techstack_id = techstack_id;
        this.project_id = project_id;
    }
}