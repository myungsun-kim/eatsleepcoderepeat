package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Project;
import com.ssafy.match.db.entity.Techstack;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompositeProjectTechstack implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "techstack_id")
    private Techstack techstack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public CompositeProjectTechstack(Techstack techstack, Project project) {
        this.techstack = techstack;
        this.project = project;
    }
}