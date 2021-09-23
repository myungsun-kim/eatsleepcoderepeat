package com.ssafy.match.group.entity.project;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "matching.project_techstack")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectTechstack {

    @EmbeddedId
    private CompositeProjectTechstack compositeProjectTechstack;

    @Column(name = "is_active")
    private boolean isActive;

    public void activation(){
        this.isActive = true;
    }

    public void deactivation(){
        this.isActive = false;
    }
    @Builder
    public ProjectTechstack(
        CompositeProjectTechstack compositeProjectTechstack, boolean isActive) {
        this.compositeProjectTechstack = compositeProjectTechstack;
        this.isActive = isActive;
    }
}