package com.ssafy.match.chat.controller;

import com.ssafy.match.chat.dto.ChatMessage;
//import com.ssafy.match.chat.service.ChatReceiverServiceImpl;
import com.ssafy.match.chat.service.ChatReceiverServiceImpl;
import com.ssafy.match.chat.service.ChatSenderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@AllArgsConstructor
public class MessageSocketController {
    @Autowired
    private ChatSenderServiceImpl sender;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatReceiverServiceImpl.class);

    //TODO : Constructor DI, Depend on Interface, not the Class.
//    @Autowired
//    private ChatReceiverServiceImpl receiver;

    private static String BOOT_TOPIC = "test1";

    //// "url/app/message"로 들어오는 메시지를 "/topic/public"을 구독하고있는 사람들에게 송신
    @MessageMapping("/pub/{toUserPk}")//@MessageMapping works for WebSocket protocol communication. This defines the URL mapping.
    public void pubMessage(@Payload ChatMessage message, @DestinationVariable String toUserPk) throws Exception {
        System.out.println(message.getRead_time());

        //
        int destPk = 0; // jwt에서 까서 pk를 넣든지 아니면 db가서 가져오든지
        try{
            destPk = Integer.parseInt(toUserPk);
        }catch (Exception e){
            LOGGER.error("Destination Variable toUserPk is not int");
            // 메세지 전송에 문제가 있습니다.
            return;
        }
        message.setId(destPk);
        sender.send(/*BOOT_TOPIC,*/ message);
    }

//    @MessageMapping("/file")
//    @SendTo("/topic/chatting")
//    public ChattingMessage sendFile(ChattingMessage message) throws Exception {
//        return new ChattingMessage(message.getFileName(), message.getRawData(), message.getUser());
//    }



//    private final SimpMessagingTemplate simpMessagingTemplate;


}
