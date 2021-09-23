package com.ssafy.match.db.entity;

import com.ssafy.match.group.entity.project.ProjectApplicationForm;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.techstack")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Techstack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Techstack(String name) {
        this.name = name;
    }
}