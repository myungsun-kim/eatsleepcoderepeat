package com.ssafy.match.controller;

import com.ssafy.match.service.StorageService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private StorageService storageService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) throws IOException {

        storageService.store(file);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

//    @PostMapping("/regist")
//    public String regist(@RequestPart(required = false) MultipartFile file) throws IllegalStateException, IOException {
//        if(file!=null && file.getSize()>0) {
//            Resource res = resourceLoader.getResource("resources/upload"); // 전달될 파일이 저장될 경로
//            book.setImg(System.currentTimeMillis()+"_"+file.getOriginalFilename()); // 실제 스토리지에 담고 있을 경로
//            book.setOrgImg(file.getOriginalFilename());//업로드 될 당시의 파일 이름
//            file.transferTo(new File(res.getFile().getCanonicalPath()+"/"+book.getImg()));
//            //개발 환경의 canonical 물리적인 패스
//        }
//        return "regist_result";
//    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> serveFile(@RequestParam(value="filename") String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
