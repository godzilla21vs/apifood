package com.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.model.Nutritionniste;
import com.food.repositories.NutritionnisteRepositorie;



@Service
public class NutritionnisteService {

	@Autowired
	NutritionnisteRepositorie nrepo;
	//s'enregistrer
	public void AddNutritio(Nutritionniste nutritioniste) {
		nrepo.save(nutritioniste);
	}
	
	//avoir la liste des nutritionistes
	public List<Nutritionniste> findAll() {
		return nrepo.findAll();
	}
	
	//rechercher un nutritioniste
	public Nutritionniste searchNutritio(int id) {
		return nrepo.findById(id).orElse(null);
	}
	
	//rechercher les nutritionnistes en fonction de leur spécialité
	public List<Nutritionniste> searchNutritio(String specialité) {
		return nrepo.findAllBySpecialite(specialité);
	}
	
	//supprimer un compte
	public void deletNutritio(int id) {
		nrepo.deleteById(id);
	}
	
	
	
}
