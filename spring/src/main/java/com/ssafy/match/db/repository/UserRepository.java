package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}