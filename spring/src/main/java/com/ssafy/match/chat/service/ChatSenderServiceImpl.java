package com.ssafy.match.chat.service;

import com.ssafy.match.chat.dto.ChatMessage;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.internals.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ChatSenderServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatSenderServiceImpl.class);

//    @Autowired
    private KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public void send(String topic, ChatMessage data) {
        LOGGER.info("sending data='{}' to topic='{}'", data, topic);
        kafkaTemplate.send(topic, data);
        // Kafka Template에 save 등 구현하기
    }
}
