package com.ssafy.match.group.repository.club;

import com.ssafy.match.group.entity.club.Club;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClubRepository extends JpaRepository<Club, Long> {
    @Query("select c from matching.club c where c.isActive = true and c.isPublic = true")
    List<Club> findAllClub();
}