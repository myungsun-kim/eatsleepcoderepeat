package com.ssafy.match.chat.service;

import com.ssafy.match.chat.dao.MessageRepository;
import com.ssafy.match.chat.dto.ChatMessage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
//@NoArgsConstructor
@Setter
public class ChatPersistentServiceImpl {
    private final MessageRepository messageRepository;


    public List<ChatMessage> getMessageById(int id){
        return messageRepository.findBySenderIdOrReceiverId(id,id);
    }

    public int setMessage(ChatMessage msg){
        try{
            messageRepository.save(msg);
        }catch(DataAccessException e){
            e.printStackTrace();
            System.out.println("!");
            return 0;
        }
        return 1;
    }

//    public List<ChatMessage>
}
