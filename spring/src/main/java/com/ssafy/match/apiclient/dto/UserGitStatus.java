package com.ssafy.match.apiclient.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGitStatus {
    public UserGitStatus(String language){
        this.language = language;
    }
    private String language;
    long code;
    long files;
    long blank;
    long comment;

    public void addCode(long code){
        this.code += code;
    }

    public void addFiles(long files){
        this.files += files;
    }

    public void addBlank(long blank){
        this.blank += blank;
    }

    public void addComment(long comment){
        this.comment += comment;
    }
}
