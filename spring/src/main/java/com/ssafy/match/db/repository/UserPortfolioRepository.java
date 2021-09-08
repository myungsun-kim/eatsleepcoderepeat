package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.MemberPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPortfolioRepository extends JpaRepository<MemberPortfolio, Integer> {

}