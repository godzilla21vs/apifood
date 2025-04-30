package com.food.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.model.Patient;

@Repository
public interface PatientRepositorie extends JpaRepository<Patient, Integer> {

	List<Patient> findAllByTyperegime(String regime);

}
