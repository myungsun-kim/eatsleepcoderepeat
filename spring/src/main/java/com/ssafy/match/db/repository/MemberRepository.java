package com.ssafy.match.db.repository;

import com.ssafy.match.controller.dto.MemberInfoDto;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.file.entity.DBFile;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);


//    @Query("select new com.ssafy.match.controller.dto.MemberInfoDto(m.email, m.name, m.nickname, m.tel, m.bio, m.city, m.position, m.dbFile, m.myClubList) from Member m")
//    List<MemberInfoDto> findMemberInfoDto();
}