package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeProjectTechstack;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.project_techstack")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectTechstack {

    @EmbeddedId
    private CompositeProjectTechstack compositeProjectTechstack;

    public ProjectTechstack(
        CompositeProjectTechstack compositeProjectTechstack) {
        this.compositeProjectTechstack = compositeProjectTechstack;
    }
}