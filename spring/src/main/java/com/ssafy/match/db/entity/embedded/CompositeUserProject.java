package com.ssafy.match.db.entity.embedded;

import com.ssafy.match.db.entity.Project;
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
public class CompositeUserProject implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project_id;

    public CompositeUserProject() {
    }

    public CompositeUserProject(User user_id, Project project_id) {
        this.user_id = user_id;
        this.project_id = project_id;
    }
}