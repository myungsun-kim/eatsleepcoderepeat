package com.ssafy.match.db.entity;

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
@Entity(name = "matching.member_sns")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String sns_type;
    private String sns_address;

    public MemberSns(Member member, String sns_type, String sns_address) {
        this.member = member;
        this.sns_type = sns_type;
        this.sns_address = sns_address;
    }
}