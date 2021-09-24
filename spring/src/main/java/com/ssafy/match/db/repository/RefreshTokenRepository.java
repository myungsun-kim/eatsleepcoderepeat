package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
//    Optional<RefreshToken> findByKey(String key);
    Optional<RefreshToken> findByKey(String key);
}