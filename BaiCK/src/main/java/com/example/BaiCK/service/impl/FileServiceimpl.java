package com.example.BaiCK.service.impl;
import com.example.BaiCK.service.util.FileUtil;
import org.springframework.util.StringUtils;
import com.example.BaiCK.domain.FileImage;
import com.example.BaiCK.repository.FileRepository;
import com.example.BaiCK.rest.response.FileRes;
import com.example.BaiCK.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceimpl implements FileService {
    @Autowired
    FileRepository fileRepository;
    private final Path root = Paths.get("uploads");


    @Override
    public FileRes UploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        long size = file.getSize();
       String code= FileUtil.saveFile(fileName, file);
        String path ="http://192.168.10.134:8080/api/image/downloadFile/"+code;
        FileImage fileImage = FileImage.builder()
                .name(fileName)
                .url(path)
                .size(size)
                .build();
        fileImage = fileRepository.save(fileImage);
        FileRes response = FileRes.builder()
                .name(fileImage.getName())
                .url(fileImage.getUrl())
                .size(fileImage.getSize())
                .build();
        return response;
    }
}
