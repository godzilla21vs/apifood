package com.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.model.Message;
import com.food.repositories.MessageRepositorie;

@Service
public class MessageService {
	@Autowired
	MessageRepositorie smsrepo;
	
	//envoyer un message
	public void sendMessage(Message sms) {
		smsrepo.save(sms);
		
	}
	//recupere la liste des message d'un patient ou d'un nutritionniste
	public List<Message> findPersonmessage(int idpatient,int idnutri){
		
		return smsrepo.findAllByPatientIdAndNutritionnisteId(idpatient,idnutri);	
	}
	
	
	

		
	//supprimer un message
		public void DeletMessage(int id) {
			smsrepo.deleteById(id);
		}
		
	//rechercher un message
		public Message searcheMessage(int id) {
			return smsrepo.findById(id).orElse(null);
		}
		
		//mise à jour d'un message
		public void updateMessage(Message message) {
			Message sms=new Message();
			sms=searcheMessage(message.getId());
			if(sms!=null) {
				smsrepo.save(sms);
			}
		}
	
}
