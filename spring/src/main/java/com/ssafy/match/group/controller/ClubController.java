package com.ssafy.match.group.controller;

import com.ssafy.match.group.dto.club.request.ClubCreateRequestDto;
import com.ssafy.match.group.dto.club.request.ClubUpdateRequestDto;
import com.ssafy.match.group.dto.club.response.ClubInfoResponseDto;
import com.ssafy.match.group.service.ClubService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    @ApiOperation(value = "클럽 생성", notes = "<strong>받은 클럽 정보</strong>를 사용해서 클럽을 생성한다.")
    public ResponseEntity<Long> create(@RequestBody ClubCreateRequestDto dto) throws Exception {
        return ResponseEntity.ok(clubService.create(dto));
    }

    @PatchMapping("/{clubId}")
    @ApiOperation(value = "클럽 수정", notes = "<strong>받은 클럽 정보</strong>를 사용해서 클럽를 수정한다.")
    public ResponseEntity<HttpStatus> update(@PathVariable("clubId") Long clubId,
        @RequestBody ClubUpdateRequestDto dto) throws Exception {
        return ResponseEntity.ok(clubService.update(clubId, dto));
    }

    @DeleteMapping("/{clubId}")
    @ApiOperation(value = "클럽 삭제", notes = "<strong>받은 클럽 Id</strong>로 클럽와 포함된 멤버관계를 삭제한다.")
    public ResponseEntity<HttpStatus> delete(@PathVariable("clubId") Long clubId)
        throws Exception {
        return ResponseEntity.ok(clubService.delete(clubId));
    }

    @DeleteMapping("/{clubId}/member")
    @ApiOperation(value = "클럽 탈퇴", notes = "<strong>받은 클럽 id</strong>로 클럽에서 탈퇴한다.")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable("clubId") Long clubId) throws Exception {
        return ResponseEntity.ok(clubService.removeMember(clubId));
    }

    @GetMapping
    @ApiOperation(value = "모든 클럽 조회", notes = "모든 클럽를 작성일 기준 내림차순으로 받는다")
    public ResponseEntity<List<ClubInfoResponseDto>> getAllClub() throws Exception {
        return ResponseEntity.ok(clubService.getAllClub());
    }

    @GetMapping("/{clubId}")
    @ApiOperation(value = "클럽 상세정보 조회",
        notes = "<strong>받은 클럽 id</strong>로 해당 클럽 정보 + 수정을 위한 정보(사용자 클럽 리스트, 지역, 상태 리스트 등")
    public ResponseEntity<ClubInfoResponseDto> getOneClub(@PathVariable("clubId") Long clubId)
        throws Exception {
        return ResponseEntity.ok(clubService.getOneClub(clubId));
    }

}
