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
@DiscriminatorValue("patient")
public class Patient extends Personne{
	private String typeregime;

	

	private double poids;

	private double taille;
	private double imc;
	
	







	public String getTyperegime() {
		return typeregime;
	}


	

	public void setTyperegime(String typeregime) {
		this.typeregime = typeregime;
	}


	public double getImc() {
		return imc;
	}






	public void setImc(double imc) {
		this.imc = imc;
	}






	












	public double getPoids() {
		return poids;
	}






	public void setPoids(double poids) {
		this.poids = poids;
	}






	public double getTaille() {
		return taille;
	}






	public void setTaille(double taille) {
		this.taille = taille;
	}






	@Override
	public String toString() {
	    return "Patient [id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", email=" + email + ", pass=" + pass
	            + ", poids=" + poids + ", taille=" + taille + ", tel=" + tel + ", imc=" + imc + ", typeregime="
	            + typeregime + ", avatarurl=" + avatarurl + "]";
	}
	




	

	
	
	
}
