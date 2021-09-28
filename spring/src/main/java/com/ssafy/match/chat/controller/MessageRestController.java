package com.ssafy.match.chat.controller;

import com.ssafy.match.chat.dto.ChatMessage;
import com.ssafy.match.chat.service.ChatPersistentServiceImpl;
import com.ssafy.match.util.SecurityUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
@AllArgsConstructor
public class MessageRestController {

    private final ChatPersistentServiceImpl chatPersistentService;
    @ApiOperation(value = "유저의 pk를 가지고 받은 메세지를 가져온다.")
    @GetMapping("/messages/{toUserId}")
    @ResponseBody
//    @ApiImplicitParam(name = "userId1",
//        value="유저의 pk를 가져온다.",
//        example = "1")
    @ApiResponses(value = {
        @ApiResponse(code = 200,
            message = "성공"),
        @ApiResponse(code = 204, message = "낫파운드"),
    })
    public ResponseEntity<?> getMessageById(@PathVariable("toUserId") long userId2) {
        long userId1 = SecurityUtil.getCurrentMemberId();
        List<ChatMessage> ret = chatPersistentService.getMessageByIdInit(userId1, userId2);
        if(ret.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<List<ChatMessage>>(ret, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "주어진 메세지의 pk보다 과거의 메세지들을 불러온다")
    @GetMapping("/messages/{toUserId2}/{msgId}")
    @ApiResponses(value = {
        @ApiResponse(code = 200,
            message = "성공"),
        @ApiResponse(code = 204, message = "낫파운드"),
    })
    public ResponseEntity<?> getPastMessageById(@PathVariable("toUserId") long userId2, @PathVariable("msgId") long msgId) {
        long userId1 = SecurityUtil.getCurrentMemberId();
        List<ChatMessage> ret = chatPersistentService.getPastMessageById(userId1, userId2, msgId);
        if(ret.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<List<ChatMessage>>(ret, HttpStatus.OK);
        }
    }

    @GetMapping("/sessions/")
    public ResponseEntity<?> findSessions(){
        long userId = SecurityUtil.getCurrentMemberId();
        List<ChatMessage> ret = chatPersistentService.findSessions(userId);
        if(ret.isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ChatMessage>>(ret, HttpStatus.OK);
    }
}