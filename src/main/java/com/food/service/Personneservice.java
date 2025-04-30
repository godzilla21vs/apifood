package com.food.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.model.Nutritionniste;
import com.food.model.Patient;
import com.food.model.Personne;
import com.food.repositories.PersonneRepositorie;

@Service
public class Personneservice {

	@Autowired
	PersonneRepositorie prepo;
	//authentification
	public Personne Authentification(String email, String pass) {
		return prepo.findByEmailAndPass(email, pass).orElse(null);
	}
	
	
	
	
	
	
	
	
	
	
}
