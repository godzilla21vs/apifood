package com.food.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.model.Nutritionniste;

@Repository
public interface NutritionnisteRepositorie extends JpaRepository<Nutritionniste, Integer>{

	List<Nutritionniste> findAllBySpecialite(String specialité);

}
