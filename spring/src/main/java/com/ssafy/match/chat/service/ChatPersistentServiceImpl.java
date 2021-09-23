package com.ssafy.match.chat.service;

import com.ssafy.match.chat.config.TuningValuesConfig;
import com.ssafy.match.chat.dao.MessageRepository;
import com.ssafy.match.chat.dto.ChatMessage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
//@NoArgsConstructor
@Setter
public class ChatPersistentServiceImpl {
    private final MessageRepository messageRepository;
    private final int RETRY_COUNT = 5;
    private final int CACHE_SIZE = TuningValuesConfig.CACHE_SIZE;

    RedisTemplate<String, Object> redisTemplate;

//    @Cacheable(key="#id", value="message")
    public List<ChatMessage> getMessageByIdInit(long id1, long id2){
        final ListOperations<String, Object> strObjListOps = redisTemplate.opsForList();
        final String redisKey = concatChatKey(id1,id2);
        if(redisTemplate.hasKey(redisKey)){
            return (List<ChatMessage>)(Object)strObjListOps.range(redisKey,0,strObjListOps.size(redisKey));
        }
        List<ChatMessage> ret = messageRepository.findBySenderIdOrReceiverId(id1,id2);
        for(ChatMessage msg : ret){
            strObjListOps.rightPush(redisKey, msg);
        }
        return setMessageToRedisInit(id1,id2);
    }

    public List<ChatMessage> getPastMessageById(long id1, long id2, long msgId){
        List<ChatMessage> ret = messageRepository.findMoreMessageBySenderIdOrReceiverId(id1,id2, msgId);
        return ret;
    }

    private List<ChatMessage> setMessageToRedisInit(long id1, long id2){
        final ListOperations<String, Object> strObjListOps = redisTemplate.opsForList();
        List<ChatMessage> ret = messageRepository.findBySenderIdOrReceiverId(id1,id2);
        if(redisTemplate.hasKey(concatChatKey(id1,id2))){
            redisTemplate.delete(concatChatKey(id1,id2));
        }
        for(ChatMessage msg : ret){
            strObjListOps.rightPush(concatChatKey(id1,id2), msg);
        }
        return ret;
    }
    public void setMessage(ChatMessage msg){
        System.out.println(msg.getType());
        // 만약 특정 메세지를 읽었다는 표시를 할 경우 저장하지 않고 db 업데이트를 수행함
//        if(msg.getType() == 2){
//            System.out.println("update");
//            messageRepository.updateRead(msg.getRead_time(), msg.getReceiverId());
//            this.setMessageToRedisInit(msg.getReceiverId());
//            return;
//        }

        ChatMessage saved = null;
        for(int i = 0; i < RETRY_COUNT; i++){
            try{
                saved = messageRepository.save(msg);
                break;
            }catch(DataAccessException e){
                e.printStackTrace();
                System.out.println("!");
            }
        }
        pushMessageToRedis(saved, saved.getReceiverId(), saved.getSenderId());
        pushMessageToRedis(saved, saved.getSenderId(), saved.getReceiverId());
    }
    public List<ChatMessage> findSessions(long id){
        return messageRepository.findSessions(id);
    }
    private void pushMessageToRedis(ChatMessage msg, long key1, long key2){
        final ListOperations<String, Object> strObjListOps = redisTemplate.opsForList();
        String redisKey = concatChatKey(key1, key2);
        if(!redisTemplate.hasKey(redisKey)){
            setMessageToRedisInit(key1, key2);
        }
        strObjListOps.rightPush(redisKey, msg);
        long cacheSize = strObjListOps.size(redisKey);
        if(cacheSize > CACHE_SIZE){
            strObjListOps.trim(
                redisKey,
                cacheSize-CACHE_SIZE-1,
                cacheSize-1
            );
        }
    }
    private String concatChatKey(long key1, long key2){
        StringBuilder sb = new StringBuilder("chat:");
        sb.append(key1).append(':').append(key2);
        return sb.toString();
    }
}
