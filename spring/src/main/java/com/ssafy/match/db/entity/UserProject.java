package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeUserProject;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.user_project")
public class UserProject {

    @EmbeddedId
    private CompositeUserProject compositeUserProject;

    private boolean is_active;
    private LocalDateTime register_date;
    private boolean authority;

    public UserProject() {
    }

    public UserProject(CompositeUserProject compositeUserProject, boolean is_active,
        LocalDateTime register_date, boolean authority) {
        this.compositeUserProject = compositeUserProject;
        this.is_active = is_active;
        this.register_date = register_date;
        this.authority = authority;
    }
}