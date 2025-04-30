package com.food.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.model.Recommandation;


@Repository
public interface RecommandationRepositorie extends JpaRepository<Recommandation, Integer> {

	List<Recommandation> findAllByPatientId(int idp);

}
