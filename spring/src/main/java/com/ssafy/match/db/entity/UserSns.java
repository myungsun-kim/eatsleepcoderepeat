package com.ssafy.match.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.user_sns")
public class UserSns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_sns_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    private String sns_type;
    private String sns_address;

    public UserSns() {
    }

    public UserSns(int user_sns_id, User user_id, String sns_type, String sns_address) {
        this.user_sns_id = user_sns_id;
        this.user_id = user_id;
        this.sns_type = sns_type;
        this.sns_address = sns_address;
    }
}