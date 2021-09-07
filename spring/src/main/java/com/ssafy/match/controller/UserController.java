package com.ssafy.match.controller;

import com.ssafy.match.db.entity.User;
import com.ssafy.match.db.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public String join(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return user.getNickname() + "님의 회원가입을 환영합니다?";
    }

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
