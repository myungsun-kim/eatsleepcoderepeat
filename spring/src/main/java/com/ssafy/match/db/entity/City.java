package com.ssafy.match.db.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum City {
    서울, 대전, 구미, 광주, 부천;

    @JsonCreator
    public static City from(String s) {
        return City.valueOf(s.toUpperCase());
    }
}
