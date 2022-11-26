package com.example.BaiCK.service;

import com.example.BaiCK.rest.response.FileRes;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


public interface FileService {
    FileRes UploadFile(MultipartFile file) throws IOException ;
}
