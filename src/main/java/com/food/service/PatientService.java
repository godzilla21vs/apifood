package com.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.model.Patient;
import com.food.repositories.PatientRepositorie;

@Service
public class PatientService {
	
	@Autowired
	PatientRepositorie prepo;
	//s'enregistrer
	public void AddPatient(Patient patient) {
		prepo.save(patient);
	}
	
	//avoir la liste des patients
	public List<Patient> findAll() {
		return prepo.findAll();
	}
	
	//rechercher un patient
	public Patient searchPatient(int id) {
		return prepo.findById(id).orElse(null);
	}
	
	//rechercher les patients en fonction de leur régime
	public List<Patient> searchPatientregime(String regime) {
		return prepo.findAllByTyperegime(regime);
	}
	
	//supprimer un patient
	public void deletPatient(int id) {
		prepo.deleteById(id);
	}
	

}
