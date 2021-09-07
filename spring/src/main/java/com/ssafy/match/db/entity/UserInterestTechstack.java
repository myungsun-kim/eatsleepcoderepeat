package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserTechstack;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.user_interest_techstack")
public class UserInterestTechstack{

    @EmbeddedId
    private CompositeUserTechstack compositeUserTechstack;

    public UserInterestTechstack() {
    }

    public UserInterestTechstack(
        CompositeUserTechstack compositeUserTechstack) {
        this.compositeUserTechstack = compositeUserTechstack;
    }
}