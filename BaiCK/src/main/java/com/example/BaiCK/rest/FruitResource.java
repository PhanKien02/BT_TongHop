package com.example.BaiCK.rest;

import java.util.List;

import com.example.BaiCK.rest.request.FruitReq;
import com.example.BaiCK.rest.response.FruitRes;
import com.example.BaiCK.service.FruitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class FruitResource {
	 private final Logger logger = LoggerFactory.getLogger(FruitResource.class);
	 
	 @Autowired
	 FruitService fruitService;
	 
	 @GetMapping("fruits")
	 public List<FruitRes> getAllFruit() {
		 logger.debug("REST request to get all Fruit");
		List<FruitRes> fruitRes = fruitService.getAllFruit();
		return fruitRes;
		
	}
	 @PostMapping("fruits")
	 public FruitRes creatFruits(@Valid @RequestBody FruitReq fruitReq) {
		 logger.debug("REST request to creat Fruit");
		 FruitRes fruitRes = fruitService.createFruit(fruitReq);
		 return fruitRes;
	}

	@PutMapping("fruits")
	public FruitRes updateFruit(@Valid @RequestBody FruitReq fruitReq){
		logger.debug("REST request to update Fruit");
		FruitRes fruitRes = fruitService.updateFruit(fruitReq);
		return fruitRes;
	}
	@DeleteMapping("fruits/{id}")
	public void deleteFruit(@Valid @PathVariable Long id){
		logger.debug("REST request to update Fruit");
		fruitService.deleteFruit(id);
	}
}
