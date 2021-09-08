package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.MemberSns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSnsRepository extends JpaRepository<MemberSns, Integer> {

}