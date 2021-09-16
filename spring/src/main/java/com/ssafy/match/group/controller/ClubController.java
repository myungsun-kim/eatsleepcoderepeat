package com.ssafy.match.group.controller;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.ClubRequestDto;
import com.ssafy.match.group.entity.Club;
import com.ssafy.match.group.repository.ClubRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    DBFileRepository dbFileRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    MemberRepository memberRepository;

    @PostMapping
    @ApiOperation(value = "클럽 생성", notes = "<strong>받은 클럽 정보</strong>를 사용해서 클럽을 생성한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<?> createClub(@RequestBody ClubRequestDto dto){

        Member member = memberRepository.getById(dto.getHost_id());

        Club club = new Club(dto.getName(), member, LocalDateTime.now(), dto.getBio(),
            dto.getCity(), 1, dto.getMax_count(), dto.is_public(), true, null);

        if (dto.getUuid() != null) {
            club.setDbFile(dbFileRepository.getById(dto.getUuid()));
        }

        clubRepository.save(club);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "클럽 수정", notes = "<strong>받은 클럽 정보</strong>를 사용해서 클럽을 수정한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<?> updateClub(@RequestBody ClubRequestDto dto, @PathVariable("id") Long id){

        Club club = clubRepository.getById(id);

        Member member = memberRepository.getById(dto.getHost_id());

        club.setName(dto.getName());
        club.setMember(member);
        club.setBio(dto.getBio());
        club.setCity(dto.getCity());
        club.setMax_count(dto.getMax_count());
        club.set_public(dto.is_public());

        if (dto.getUuid() != null) {
            club.setDbFile(dbFileRepository.getById(dto.getUuid()));
        } else {
            club.setDbFile(null);
        }

        clubRepository.save(club);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "클럽 삭제", notes = "<strong>받은 클럽 Id</strong>로 클럽을 삭제한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity deleteClub(@PathVariable("id") Long id){

        Club club = clubRepository.getById(id);
        club.set_active(false);

        clubRepository.save(club);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
