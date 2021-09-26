package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    List<Position> findAllByMember(Member member);

    boolean existsByMemberAndName(Member member, String name);
}