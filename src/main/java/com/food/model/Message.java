package com.food.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Message {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	@Column(length = 1000)
	private String contenupatient;
	@Column(length = 1000)
	private String contenunutri;
	private Date date;
	private String expediteur;
	@ManyToOne
	private Patient patient;
	@ManyToOne
	private Nutritionniste nutritionniste;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContenupatient() {
		return contenupatient;
	}
	public void setContenupatient(String contenupatient) {
		this.contenupatient = contenupatient;
	}
	public String getContenunutri() {
		return contenunutri;
	}
	public void setContenunutri(String contenunutri) {
		this.contenunutri = contenunutri;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getExpediteur() {
		return expediteur;
	}
	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
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
	@Override
	public String toString() {
	    return "Message [id=" + id + ", contenupatient=" + contenupatient + ", contenunutri=" + contenunutri + ", date="
	            + date + ", expediteur=" + expediteur + "]";
	}
	
	
	
	
	
}
