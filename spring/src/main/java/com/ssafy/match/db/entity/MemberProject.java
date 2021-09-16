package com.ssafy.match.db.entity;

import com.ssafy.match.db.entity.embedded.CompositeMemberProject;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.member_project")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProject {

    @EmbeddedId
    private CompositeMemberProject compositeMemberProject;

    private String role;
    private boolean is_active;
    private LocalDateTime register_date;
    private boolean authority;

    @Builder
    public MemberProject(
        CompositeMemberProject compositeMemberProject, String role, boolean is_active,
        LocalDateTime register_date, boolean authority) {
        this.compositeMemberProject = compositeMemberProject;
        this.role = role;
        this.is_active = is_active;
        this.register_date = register_date;
        this.authority = authority;
    }
}