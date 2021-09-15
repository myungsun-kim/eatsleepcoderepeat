package com.ssafy.match.chat.dao;

import com.ssafy.match.chat.config.TuningValuesConfig;
import com.ssafy.match.chat.dto.ChatMessage;
import com.ssafy.match.db.entity.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<ChatMessage, Integer> {
    @Query(value =
        "select * from matching.message order by id ASC LIMIT "
            + TuningValuesConfig.CACHE_SIZE,
        nativeQuery = true)
    List<ChatMessage> findBySenderIdOrReceiverId(long id1, long id2);
}
