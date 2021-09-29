package com.ssafy.match.file.controller;

import com.ssafy.match.file.dto.UploadFileResponse;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.service.DBFileStorageService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

//    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private DBFileStorageService dbFileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {

        DBFile dbFile = dbFileStorageService.storeFile(file);

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//            .path("/file/downloadFile/")
//            .path(dbFile.getId())
//            .toUriString();

        return new UploadFileResponse(dbFile.getId(), dbFile.getFile_name(), dbFile.getDownload_uri(),
            file.getContentType(), file.getSize());

    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(dbFile.getFile_type()))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFile_name() + "\"")
            .body(new ByteArrayResource(dbFile.getData()));

    }

}
