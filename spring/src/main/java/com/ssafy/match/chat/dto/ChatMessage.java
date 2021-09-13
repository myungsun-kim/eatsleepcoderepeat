package com.ssafy.match.chat.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "matching.message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

//    @OneToOne(name = "id")
//    @JoinColumn(name = "sender_id")
    @Column(name = "sender_id")
    int senderId;
    @Column(name = "receiver_id")
    int receiverId;
    Timestamp sent_time;
    Timestamp read_time;
//    String message;
    String content;
}
