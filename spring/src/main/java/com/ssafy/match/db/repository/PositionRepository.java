package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {

}