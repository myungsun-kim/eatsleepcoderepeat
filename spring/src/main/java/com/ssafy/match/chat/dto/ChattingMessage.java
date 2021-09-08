package com.ssafy.match.chat.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChattingMessage implements Serializable {
    private String message;
    private String user;
    private Long timeStamp;

    private String fileName;
    private String rawData;

    public ChattingMessage() {
    }

    public ChattingMessage(String message, String user) {
        this.user = user;
        this.message = message;
    }

    public ChattingMessage(String fileName, String rawData, String user) {

        this.fileName = fileName;
        this.rawData = rawData;
        this.user = user;
    }

    public ChattingMessage(String message) {
        this.message = message;
    }
}