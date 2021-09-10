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

    private static String BOOT_TOPIC = "test";

    //// "url/app/message"로 들어오는 메시지를 "/topic/public"을 구독하고있는 사람들에게 송신
    @MessageMapping("/pub/{toUserPk}")//@MessageMapping works for WebSocket protocol communication. This defines the URL mapping.
////    @SendTo("/topic/public")//websocket subscribe topic& direct send
    public void sendMessage(@Payload ChatMessage message, @DestinationVariable String toUserPk) throws Exception {
        int destPk = 0;
        try{
            destPk = Integer.parseInt(toUserPk);
        }catch (Exception e){
            LOGGER.error("Destination Variable toUserPk is not int");
            return;
        }
////        message.setTimeStamp(System.currentTimeMillis());
////        chattingHistoryDAO.save(message);
        System.out.println(message);

        System.out.println(destPk);
        message.setPk_idx(destPk);
        System.out.println(message.getMessage());
        sender.send(BOOT_TOPIC, message);
//
    }

//    @MessageMapping("/pub/{sessionId}")  // 클라이언트가 메세지를 보내는 주소
//    @SendTo("/sub/{sessionId}")
//    public String SocketHandler(String message,
//        @DestinationVariable String sessionId) {
//        try {
//            // cache
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(message);
//        return message;
//    }

//    @RequestMapping("/history")
//    public List<ChattingMessage> getChattingHistory() throws Exception {
//        System.out.println("history!");
//        return chattingHistoryDAO.get();
//    }

//    @MessageMapping("/file")
//    @SendTo("/topic/chatting")
//    public ChattingMessage sendFile(ChattingMessage message) throws Exception {
//        return new ChattingMessage(message.getFileName(), message.getRawData(), message.getUser());
//    }



//    private final SimpMessagingTemplate simpMessagingTemplate;


}
