package com.ssafy.match.file.service;

import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.exception.FileStorageException;
import com.ssafy.match.file.exception.MyFileNotFoundException;
import com.ssafy.match.file.repository.DBFileRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
            .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
