package com.ssafy.match.chat.service;

import com.ssafy.match.chat.config.TuningValuesConfig;
import com.ssafy.match.chat.dao.MessageRepository;
import com.ssafy.match.chat.dto.ChatMessage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ChatMessage> getMessageByIdInit(long id){
        final ListOperations<String, Object> strObjListOps = redisTemplate.opsForList();
//        System.out.println("호우!");
        final String redisKey = concatChatKey(id);
        if(redisTemplate.hasKey(redisKey)){
            return (List<ChatMessage>)(Object)strObjListOps.range(redisKey,0,strObjListOps.size(redisKey));
        }
        List<ChatMessage> ret = messageRepository.findBySenderIdOrReceiverId(id,id);
        for(ChatMessage msg : ret){
            strObjListOps.rightPush(redisKey, msg);
        }
        return setMessageToRedisInit(id);
    }
    private List<ChatMessage> setMessageToRedisInit(long id){
        final ListOperations<String, Object> strObjListOps = redisTemplate.opsForList();
        List<ChatMessage> ret = messageRepository.findBySenderIdOrReceiverId(id,id);
        for(ChatMessage msg : ret){
            strObjListOps.rightPush(concatChatKey(id), msg);
        }
        return ret;
    }
/*
* 레디스에 캐싱된 메세지들 {메세지1, 메세지2, 메세지3.....}
* 사람이 메세지4를 상대방에게 보내는 상황
* ID 1이 있다 -> 이미 매핑되엉 ㅣㅆ는 값이 있다
* 어디선가 id를 쓸텐데 메세지가 변경이 될 경우 이 id를 새로 따고 집어넣냐
* 기존의 아이
* 2. 만약 캐시 생명? (용어기억안남, 캐시 정책에 따라 다르겠지만 몇 초 후에 캐시가 invalid하다고 설정한 경우)
* 캐시생명이 끝나면 리스트 전체가 죽을텐데.....
* */

//    @Transactional
//    @Caching(
//        put={
//            @CachePut(key="#msg.receiverId", value="getMessageById"),
//            @CachePut(key="#msg.senderId", value="getMessageById")
//        },
//        evict={
//            @CacheEvict(key="#msg.receiverId", value="userMessages", beforeInvocation = true),
//            @CacheEvict(key="#msg.senderId", value="userMessages", beforeInvocation = true)
//        }
//    )
    public void setMessage(ChatMessage msg){
        pushMessageToRedis(msg, msg.getReceiverId());
        pushMessageToRedis(msg, msg.getSenderId());
        for(int i = 0; i < RETRY_COUNT; i++){
            try{
                messageRepository.save(msg);
                break;
            }catch(DataAccessException e){
                e.printStackTrace();
                System.out.println("!");
            }
        }
    }
    private void pushMessageToRedis(ChatMessage msg, long key){
        final ListOperations<String, Object> strObjListOps = redisTemplate.opsForList();
        String redisKey = concatChatKey(key);
        if(!redisTemplate.hasKey(redisKey)){
            setMessageToRedisInit(key);
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
    private String concatChatKey(long key){
        StringBuilder sb = new StringBuilder("chat:");
        sb.append(key);
        return sb.toString();
    }

//    public List<ChatMessage>
}
