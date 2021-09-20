package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.Techstack;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechstackRepository extends JpaRepository<Techstack, Integer> {
    Optional<Techstack> findByName(String techName);
}