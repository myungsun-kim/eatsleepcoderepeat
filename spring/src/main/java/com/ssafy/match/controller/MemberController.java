package com.ssafy.match.controller;

import com.ssafy.match.controller.dto.*;
import com.ssafy.match.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/account")
    public ResponseEntity<MemberInfoDto> getMyPage() {
        return ResponseEntity.ok(memberService.getMyPage());
    }

    @PutMapping
    public ResponseEntity<MemberUpdateResponseDto> updateMember(@RequestBody @Valid MemberUpdateRequestDto memberUpdateRequestDto) {
        return ResponseEntity.ok(memberService.updateMyInfo(memberUpdateRequestDto));
    }

//    @GetMapping("/me")
//    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
//        return ResponseEntity.ok(memberService.getMyInfo());
//    }

//    @GetMapping("/{email}")
//    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String email) {
//        return ResponseEntity.ok(memberService.getMemberInfo(email));
//    }

//    @PatchMapping
//    public ResponseEntity<MemberResponseDto> modifyMemberInfo(@RequestBody MemberModifyRequestDto dto){
//        return ResponseEntity.ok(memberService.modifyMyInfo(dto));
//    }
//    @Autowired
//    MemberRepository memberRepository;
//
//    @PostMapping("/signup")
//    public String join(@RequestBody Member member) {
//        Member newMember = memberRepository.save(member);
//        return member.getNickname() + "님의 회원가입을 환영합니다";
//    }

    @ApiOperation(value = "테스트용 API")
    @GetMapping("/example/{example_id}")
    @ResponseBody
    @ApiImplicitParam(name = "example_id",
        value="1과 0 중에 고르세요.",
        example = "1")
    @ApiResponses(value = {
        @ApiResponse(code = 200,
            message = "성공"),
//            response = String.class,
//            responseContainer = "List"),
        @ApiResponse(code = 404, message = "낫파운드"),
    })
    public ResponseEntity<?> test(@PathVariable String example_id){
        if("1".equals(example_id)){
            return new ResponseEntity<String>("example success!", HttpStatus.OK);
        }
        return new ResponseEntity<String>("example failed!", HttpStatus.NOT_FOUND);
    }

}
