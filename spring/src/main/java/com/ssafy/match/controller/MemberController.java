package com.ssafy.match.controller;

import com.ssafy.match.controller.dto.*;
import com.ssafy.match.service.MemberService;
import com.ssafy.match.util.SecurityUtil;
import io.swagger.annotations.*;
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

    @GetMapping("/mypage")
    public ResponseEntity<MemberInfoDto> getMyPage() {
        return ResponseEntity.ok(memberService.getMyPage());
    }


    @PutMapping
//    @ResponseBody
    @ApiOperation(value = "내 계정 정보 Update")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="email", value="이메일", required = true, example = "myemail@gmail.com"),
//            @ApiImplicitParam(name="password", value="비밀번호", required = true, example = "mypassword"),
//            @ApiImplicitParam(name="name", value="이름", required = true, example = "문일민"),
//            @ApiImplicitParam(name="nickname", value="닉네임", required = true, example = "별명"),
//            @ApiImplicitParam(name="tel", value="전화번호", required = false, example = "010-4134-1370"),
//            @ApiImplicitParam(name="bio", value="자기소개", required = false, example = "let me introduce my self"),
//            @ApiImplicitParam(name="city", value="도시", required = false, example = "서울"),
//            @ApiImplicitParam(name="position", value="역할", required = false, example = "개발자"),
//            @ApiImplicitParam(name="cover_pic", value="사진", required = false, example = "데이터"),
//            @ApiImplicitParam(name="expTechList", value="experienced tech list", required = false, example = "[\"python\",\"java\"]"),
//            @ApiImplicitParam(name="beginTechList", value="beginner ", required = false, example = "[\"python\",\"java\"]"),
//            @ApiImplicitParam(name="portfolio_uri", value="portfolio 경로", required = false, example = "https://naver.com")
//    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "성공"),
            @ApiResponse(code = 406, message = "데이터 에러"),
    })
    public ResponseEntity<?> updateMember(@RequestBody @Valid MemberUpdateRequestDto memberUpdateRequestDto) {
        MemberUpdateResponseDto memberUpdateResponseDto = memberService.updateMyInfo(memberUpdateRequestDto);
        if (memberUpdateResponseDto.getId().equals(SecurityUtil.getCurrentMemberId())) {
            return new ResponseEntity<String>("수정사항이 성공적으로 반영되었습니다.", HttpStatus.OK);
        }
        return new ResponseEntity<String>("수정이 실패했습니다!", HttpStatus.NOT_ACCEPTABLE);
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
