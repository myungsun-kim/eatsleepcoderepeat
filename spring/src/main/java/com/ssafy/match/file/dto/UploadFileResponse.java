package com.ssafy.match.file.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {

    private String Id;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

}
