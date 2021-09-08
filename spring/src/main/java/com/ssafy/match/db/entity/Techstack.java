package com.ssafy.match.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.techstack")
public class Techstack {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int techstack_id;

    private String name;

    public Techstack() {
    }

    public Techstack(int techstack_id, String name) {
        this.techstack_id = techstack_id;
        this.name = name;
    }
}