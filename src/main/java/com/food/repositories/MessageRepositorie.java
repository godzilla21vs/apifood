package com.food.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.model.Message;


@Repository
public interface MessageRepositorie extends JpaRepository<Message, Integer> {

	List<Message> findAllByPatientIdAndNutritionnisteId(int idpatient, int idnutri);

	void deleteByPatientId(int id);

	void deleteByNutritionnisteId(int id);

}
