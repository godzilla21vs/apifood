package com.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food.model.Recommandation;
import com.food.repositories.RecommandationRepositorie;

@Service
public class RecommandationService {
	@Autowired
	RecommandationRepositorie rcrepo;
	
	//envoyer une recomandation
	public void sendRecom(Recommandation reco) {
		rcrepo.save(reco);
	}
	
	//renvoyer la liste des recommandations pour un patient
	
	public List<Recommandation> findAllRecommandationp(int idp){
		return rcrepo.findAllByPatientId(idp);
	}
	
	//rechercher une recommandation
	public Recommandation searchRecom(int id) {
		return rcrepo.findById(id).orElse(null);
	}
	
	//update recommandation
	public void UpdateRecom(Recommandation rc) {
		if(searchRecom(rc.getId())!=null) {
			rcrepo.save(rc);
		}
	}
	

}
