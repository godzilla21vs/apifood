package com.food.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Food {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String  description;
	private double Qtglucide;
	private double Qtproteine;
	private double Qtlipide;
	private String  imageurl;
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getQtglucide() {
		return Qtglucide;
	}

	public void setQtglucide(double qtglucide) {
		Qtglucide = qtglucide;
	}

	public double getQtproteine() {
		return Qtproteine;
	}

	public void setQtproteine(double qtproteine) {
		Qtproteine = qtproteine;
	}

	public double getQtlipide() {
		return Qtlipide;
	}

	public void setQtlipide(double qtlipide) {
		Qtlipide = qtlipide;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", nom=" + nom + ", description=" + description + ", Qtglucide=" + Qtglucide
				+ ", Qtproteine=" + Qtproteine + ", Qtlipide=" + Qtlipide + ", imageurl=" + imageurl + "]";
	}
	
	
	
	
	
	
}
