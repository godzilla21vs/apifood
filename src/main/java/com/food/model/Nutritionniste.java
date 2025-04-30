package com.food.model;

import java.util.Collection;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("nutritioniste")
public class Nutritionniste extends Personne {

	
	
	private String specialite;
	@OneToMany(mappedBy = "nutritionniste")
	private Collection<Message>message;
	
	


	public String getSpecialite() {
		return specialite;
	}


	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}



	public void setMessage(Collection<Message> message) {
		this.message = message;
	}


	@Override
	public String toString() {
	    return "Nutritionniste [specialite=" + specialite + ", id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", email=" + email + ", pass=" + pass + ", tel=" + tel + ", avatarurl=" + avatarurl + "]";
	}



	

	
}






