package com.ssafy.match.chat.dao;

import com.ssafy.match.chat.dto.ChatMessage;
import com.ssafy.match.db.entity.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<ChatMessage, Integer> {
    List<ChatMessage> findBySenderIdOrReceiverId(int id1, int id2);

}
