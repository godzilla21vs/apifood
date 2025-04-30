package com.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.model.Food;
import com.food.repositories.FoodRepositorie;

@Service
public class FoodService {
	
	@Autowired
	FoodRepositorie foodRepo;
	
	//ajouter une nouriture
	public void AddFood(Food food){
		foodRepo.save(food);
		
	}
	
	//supprimer une nouriture
	public void DeletFood(int id) {
		foodRepo.deleteById(id);
	}
	
	
	
	
	public List<Food> findAllFood() {
		return foodRepo.findAll();
	}

	public Food searchFood(int idf) {
		// TODO Auto-generated method stub
		return foodRepo.findById(idf).orElse(null);
	}

	
}
