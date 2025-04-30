package com.food.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.model.Nutritionniste;
import com.food.model.Patient;
import com.food.model.Personne;


@Repository
public interface PersonneRepositorie extends JpaRepository<Personne, Integer> {

	Optional<Personne> findByEmailAndPass(String email, String pass);

}
