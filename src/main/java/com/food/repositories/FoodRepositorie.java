package com.food.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.model.Food;

@Repository
public interface FoodRepositorie extends JpaRepository<Food, Integer> {

	

}
