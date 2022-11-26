package com.example.BaiCK.rest;
import com.example.BaiCK.rest.response.FileRes;
import com.example.BaiCK.service.FileService;
import com.example.BaiCK.service.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;


@RestController
@RequestMapping("api/image")
public class FileResource {
    private final Logger log = LoggerFactory.getLogger(FileResource.class);

    @Autowired
    FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<FileRes> createFile(@RequestPart("file") MultipartFile file) throws Exception {
        log.debug("Request to upload new images");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(fileService.UploadFile(file));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(fileService.UploadFile(file));
        }
    }

    @GetMapping(value = "/downloadFile/{fileCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileUtil downloadUtil = new FileUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
