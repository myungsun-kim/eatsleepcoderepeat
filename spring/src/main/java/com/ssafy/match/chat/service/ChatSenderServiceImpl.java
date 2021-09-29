package com.ssafy.match.chat.service;

import com.ssafy.match.chat.dao.MessageRepository;
import com.ssafy.match.chat.dto.ChatMessage;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.internals.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ChatSenderServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatSenderServiceImpl.class);
    private final ChatReceiverServiceImpl chatReceiverService;
//    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
    private final ChatPersistentServiceImpl chatPersistentService;

    public void send(ChatMessage data) {
//        LOGGER.info("sending data='{}' to topic='{}'", data, topic);
        chatPersistentService.setMessage(data);
        System.out.println(data);
        try{
            chatReceiverService.receive(data);
        }
        catch(Exception e){
            e.printStackTrace();
        }
//        kafkaTemplate.send(topic, data);
        // Kafka Template에 save 등 구현하기
    }
}
