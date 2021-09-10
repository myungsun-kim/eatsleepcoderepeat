package com.ssafy.match.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.match.chat.dto.ChatMessage;
import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ChatReceiverServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatReceiverServiceImpl.class);

//    @Autowired
    private SimpMessagingTemplate template;

    //@KafkaListener 어노테이션 부착 시 -> 받아올 메세지가 있을 때 수행하는 일들을 아래 메서드에 적용한다.
    @KafkaListener(topics = "test")
    public void receive(@Payload ChatMessage message) throws Exception {
        LOGGER.info("message='{}'", message);
        HashMap<String, String> msg = new HashMap<>();
//        msg.put("timestamp", Long.toString(message.getTimeStamp()));
//        for(ChatMessage ms : message){
        msg.put("message", message.getMessage());
//        }
//        msg.put("author", message.getUser());
//        msg.put("message");
//            System.out.println(message.getMessage());
        msg.put("message", "test");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(msg);
        // 프론트의Stringify와 유사

        this.template.convertAndSend("/sub", json);
        // 실제 socket으로 메세지를 전달하는 메서드
    }
}
