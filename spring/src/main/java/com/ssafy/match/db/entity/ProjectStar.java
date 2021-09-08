package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserProject;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.project_star")
public class ProjectStar {

    @EmbeddedId
    private CompositeUserProject compositeUserProject;

    public ProjectStar() {
    }

    public ProjectStar(CompositeUserProject compositeUserProject) {
        this.compositeUserProject = compositeUserProject;
    }
}