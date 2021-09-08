package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserTechstack;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.user_techstack")
public class UserTechstack {

    @EmbeddedId
    private CompositeUserTechstack compositeUserTechstack;

    public UserTechstack() {
    }

    public UserTechstack(
        CompositeUserTechstack compositeUserTechstack) {
        this.compositeUserTechstack = compositeUserTechstack;
    }
}