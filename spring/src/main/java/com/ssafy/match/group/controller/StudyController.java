package com.ssafy.match.group.controller;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.study.StudyCreateRequestDto;
import com.ssafy.match.group.dto.study.StudyUpdateRequestDto;
import com.ssafy.match.group.entity.Study;
import com.ssafy.match.group.repository.ClubRepository;
import com.ssafy.match.group.repository.StudyRepository;
import com.ssafy.match.group.repository.StudyTechstackRepository;
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
@RequestMapping("/study")
public class StudyController {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    TechstackRepository techstackRepository;
    @Autowired
    StudyTechstackRepository studyTechstackRepository;
    @Autowired
    DBFileRepository dbFileRepository;
    @Autowired
    ClubRepository clubRepository;

    @PostMapping
    @ApiOperation(value = "스터디 생성", notes = "<strong>받은 스터디 정보</strong>를 사용해서 스터디를 생성한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<?> createStudy(@RequestBody StudyCreateRequestDto dto) {

        Member member = memberRepository.getById(dto.getHost_id());

        Study study = new Study(dto.getName(), member, LocalDateTime.now(), LocalDateTime.now(),
            dto.getPeriod(), dto.getSchedule(), dto.getBio(),
            1, dto.getMax_count(), dto.getCity(), Status.모집중,
            true, dto.is_public(), true, null, null);

        if (dto.getUuid() != null) {
            study.setDbFile(dbFileRepository.getById(dto.getUuid()));
        }
        if (dto.getClub_id() != null) {
            study.setClub(clubRepository.getById(dto.getClub_id()));
        }

        studyRepository.save(study);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "스터디 수정", notes = "<strong>받은 스터디 정보</strong>를 사용해서 스터디를 수정한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<?> updateStudy(@RequestBody StudyUpdateRequestDto dto,
        @PathVariable("id") Long id) {

        Study study = studyRepository.getById(id);
        Member member = memberRepository.getById(dto.getHost_id());

        study.setName(dto.getName());
        study.setMember(member);
        study.setSchedule(dto.getSchedule());
        study.setBio(dto.getBio());
        study.setPeriod(dto.getPeriod());
        study.setModify_date(LocalDateTime.now());
        study.setMax_count(dto.getMax_count());
        study.setCity(dto.getCity());
        study.setStatus(dto.getStatus());
        study.set_public(dto.is_public());
        study.set_participate(dto.is_participate());

        if (dto.getUuid() != null) {
            study.setDbFile(dbFileRepository.getById(dto.getUuid()));
        } else {
            study.setDbFile(null);
        }

        if (dto.getClub_id() != null) {
            study.setClub(clubRepository.getById(dto.getClub_id()));
        } else {
            study.setClub(null);
        }

        studyRepository.save(study);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "스터디 삭제", notes = "<strong>받은 스터디 Id</strong>로 스터디를 삭제한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity deleteClub(@PathVariable("id") Long id) {

        Study study = studyRepository.getById(id);
        study.set_active(false);

        studyRepository.save(study);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
