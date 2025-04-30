package com.food.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Recommandation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String Typeregime;
	@ManyToOne
	private Patient patient;
	@ManyToOne
	private Nutritionniste nutritionniste;
	@ManyToOne
	private Food food;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTyperegime() {
		return Typeregime;
	}

	public void setTyperegime(String typeregime) {
		Typeregime = typeregime;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Nutritionniste getNutritionniste() {
		return nutritionniste;
	}

	public void setNutritionniste(Nutritionniste nutritionniste) {
		this.nutritionniste = nutritionniste;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "Recommandation [id=" + id + ", Typeregime=" + Typeregime + ", patient=" + patient + ", nutritionniste="
				+ nutritionniste + ", food=" + food + "]";
	}
	
	
	
	
	
	
}


