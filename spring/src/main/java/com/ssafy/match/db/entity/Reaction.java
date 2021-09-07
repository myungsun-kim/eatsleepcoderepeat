package com.ssafy.match.db.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int reaction_id;

    private String name;
    private String bytecode;

    public Reaction() {
    }

    public Reaction(int reaction_id, String name, String bytecode) {
        this.reaction_id = reaction_id;
        this.name = name;
        this.bytecode = bytecode;
    }
}