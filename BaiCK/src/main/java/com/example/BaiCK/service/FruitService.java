package com.example.BaiCK.service;


import com.example.BaiCK.rest.request.FruitReq;
import com.example.BaiCK.rest.response.FileRes;
import com.example.BaiCK.rest.response.FruitRes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FruitService {

    List<FruitRes> getAllFruit();

    FruitRes getFruitbyid(Long id);
    FruitRes createFruit(FruitReq fruitReq);

    FruitRes updateFruit(FruitReq fruitReq);

    void deleteFruit(long id);

}
