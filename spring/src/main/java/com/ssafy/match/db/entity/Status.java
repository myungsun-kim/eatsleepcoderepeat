package com.ssafy.match.db.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    진행중, 모집중, 종료됨;

    @JsonCreator
    public static Status from(String s) {
        return Status.valueOf(s.toUpperCase());
    }
}
