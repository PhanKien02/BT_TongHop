package com.example.BaiCK.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.BaiCK.domain.Fruit;
import com.example.BaiCK.exception.Exception;
import com.example.BaiCK.repository.FruitRepository;
import com.example.BaiCK.rest.request.FruitReq;
import com.example.BaiCK.rest.response.FruitRes;
import com.example.BaiCK.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceimpl implements FruitService {

	@Autowired
	FruitRepository fruitRepository;



	@Override
	public List<FruitRes> getAllFruit() {
		List<Fruit> fruits = fruitRepository.findAll();
		List<FruitRes> fruitRests = new ArrayList<>();
		for(Fruit fruit : fruits) {
			FruitRes fruitRes = FruitRes.builder()
					.id(fruit.getId())
					.title(fruit.getTitle())
					.description(fruit.getDescription())
					.thumbnail(fruit.getThumbnail())
					.build();
			fruitRests.add(fruitRes);			
		}
		
		return fruitRests;
	}

	@Override
	public FruitRes getFruitbyid(Long id) {
		Optional<Fruit> fruiOptional = fruitRepository.findById(id);
		if(fruiOptional.isEmpty())
		{
			throw new Exception(404,"not found fruit by id= "+id);
		}
		Fruit fruit = fruiOptional.get();
		FruitRes fruitRes = FruitRes.builder()
				.id(fruit.getId())
				.title(fruit.getTitle())
				.description(fruit.getDescription())
				.thumbnail(fruit.getThumbnail())
				.build();
		return fruitRes;
	}

	@Override
	public FruitRes createFruit(FruitReq fruitReq) {
		Fruit fruit = Fruit.builder()
				.title(fruitReq.getTitle())
				.thumbnail(fruitReq.getThumbnail())
				.description(fruitReq.getDescription())
				.build();
		fruit = fruitRepository.save(fruit);
		
		FruitRes fruitRes = FruitRes.builder()
				.id(fruit.getId())
				.title(fruit.getTitle())
				.description(fruit.getDescription())
				.thumbnail(fruit.getThumbnail())
				.build();
		return fruitRes;
	}

	@Override
	public FruitRes updateFruit(FruitReq fruitReq) {
		Optional<Fruit> fruiOptional = fruitRepository.findById(fruitReq.getId());
		if(fruiOptional.isEmpty())
		{
			throw new Exception(404,"not found fruit by id= "+fruitReq.getId());
		}
		Fruit fruit = fruiOptional.get();
		fruit =Fruit.builder()
				.id(fruitReq.getId())
				.title(fruitReq.getTitle())
				.description(fruitReq.getDescription())
				.thumbnail(fruitReq.getThumbnail())
				.build();

		fruit = fruitRepository.save(fruit);

		FruitRes fruitRes = FruitRes.builder()
				.id(fruit.getId())
				.title(fruit.getTitle())
				.description(fruit.getDescription())
				.thumbnail(fruit.getThumbnail())
				.build();	
		return fruitRes;
	}

	@Override
	public void deleteFruit(long id) {
		Optional<Fruit> fruiOptional = fruitRepository.findById(id);
		if(fruiOptional.isEmpty())
		{
			throw new Exception(404, "not found fruit by id="+id);
		}
		fruitRepository.delete(fruiOptional.get());
	}
}
