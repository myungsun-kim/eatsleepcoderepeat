package com.ssafy.match.group.clubboard.board.entity;

import com.ssafy.match.group.entity.club.Club;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "club_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    private String name;

    @Builder
    public ClubBoard(Club club, String name) {
        this.club = club;
        this.name = name;
    }
}
