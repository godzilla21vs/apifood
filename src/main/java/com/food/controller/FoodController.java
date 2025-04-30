package com.food.controller;

import java.io.File;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.food.model.Food;
import com.food.model.Message;
import com.food.model.Nutritionniste;
import com.food.model.Patient;
import com.food.model.Personne;
import com.food.model.Recommandation;
import com.food.service.FoodService;
import com.food.service.MessageService;
import com.food.service.NutritionnisteService;
import com.food.service.PatientService;
import com.food.service.Personneservice;
import com.food.service.RecommandationService;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "*")
public class FoodController {

	@Autowired
	FoodService fservice;
	//Enregistrer une nouriture
	@Autowired
    private Cloudinary cloudinary;
	@PostMapping("/savefood")
	public void SaveFood(@RequestParam("nom")String nom,@RequestParam("description")String description,
			@RequestParam("qtglucide")double qtglucide,@RequestParam("qtproteine")double qtproteine,
			@RequestParam("qtlipide")double qtlipide,@RequestParam("photo")MultipartFile photo) {
		
		String fileName=StringUtils.cleanPath(photo.getOriginalFilename());
		try {
			Map uploadResult = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");

            Food fd = new Food();
            fd.setNom(nom);
            fd.setDescription(description);
            fd.setQtglucide(qtglucide);
            fd.setQtlipide(qtlipide);
            fd.setQtproteine(qtproteine);
            fd.setImageurl(imageUrl);
            fservice.AddFood(fd);
		   
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	//afficher la liste des nouritures
	@GetMapping("/")
	public List<Food> Allfood() {
		return fservice.findAllFood();
		
	}
	
  //supprimer une nourriture
	@DeleteMapping("/deletfood/{id}")
	public void deletFood(@PathVariable("id")int id) {
		fservice.DeletFood(id);
	}
	
	//rechercher une liste de nourriture riche en nutriment
	@GetMapping("/searchfoods/{nutriment}")
	public List<Food> searchfood(@PathVariable("nutriment")String regime){
		
		List<Food> food=fservice.findAllFood();
		
		List<Food> food1=new ArrayList<Food>()   ;
		
		int i;
		for(i=0;i<food.size();i++) {
			if(regime.equals("glucide")) {
				
				if(food.get(i).getQtglucide()>=food.get(i).getQtlipide()&&food.get(i).getQtglucide()>=food.get(i).getQtproteine()) {
					food1.add(food.get(i));
				}
			}else if(regime.equals("protéine")) {
				
				if(food.get(i).getQtproteine()>=food.get(i).getQtlipide()&&food.get(i).getQtproteine()>=food.get(i).getQtglucide()) {
					food1.add(food.get(i));
				}
			}else if(regime.equals("lipide")) {
				
				if(food.get(i).getQtlipide()>=food.get(i).getQtglucide()&&food.get(i).getQtlipide()>=food.get(i).getQtproteine()) {
					food1.add(food.get(i));
				}
				
			}
		}
		return food1;
		
	}
	
	
	@Autowired
	MessageService smsserv;
	@Autowired 
	PatientService pt;
	@Autowired
	NutritionnisteService nt;
	

	
	
	@GetMapping("/mesmessages/{idp}/{idn}")
	public ResponseEntity<?> getMesMessages(@PathVariable int idp, @PathVariable int idn) {
	    try {
	        // Code pour récupérer les messages
	        List<Message> messages = smsserv.findPersonmessage(idp, idn);
	        return ResponseEntity.ok(messages);
	    } catch (Exception e) {
	        // Gérer l'exception de récursion infinie ici
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des messages");
	    }
	}
	
	
	//envoyer un message
	@PostMapping("/savemessage")
	public void sendMessage(@RequestParam("idp")int idp,@RequestParam("idn")int idn,
			@RequestParam("contenu")String contenu,@RequestParam("exp")String env) {
		//traitement de la date
		Nutritionniste nutri=new Nutritionniste();
		Patient patient=new Patient();
		
		nutri=nt.searchNutritio(idn);
		patient=pt.searchPatient(idp);
		Date date=new Date();
		
		Message sms=new Message();
		sms.setContenunutri(contenu);
		sms.setContenupatient(contenu);
		sms.setExpediteur(env);
		sms.setDate(date);
		sms.setNutritionniste(nutri);
		sms.setPatient(patient);
		smsserv.sendMessage(sms);
		
	}
	
	//supprimer un message de mon coté que j'ai envoyé ou recu en tant que patient ou nutritioniste
	
	@DeleteMapping("/dlMysmser/{idsms}/{idu}")
	public void deletsmsrep(@PathVariable("idsms")int idsms,@PathVariable("idu")int iduser) {
		Nutritionniste nutri=new Nutritionniste();
		Patient patient=new Patient();
		
		nutri=nt.searchNutritio(iduser);
		patient=pt.searchPatient(iduser);
		
		Message sms=new Message();
		sms=smsserv.searcheMessage(idsms);
		if(sms.getExpediteur()==patient.getEmail()) {
			sms.setContenupatient(null);
		}else if(sms.getExpediteur()!=patient.getEmail()) {
			sms.setContenupatient(null);
		}
		if(sms.getExpediteur()==nutri.getEmail()) {
			sms.setContenunutri(null);
		}else if(sms.getExpediteur()!=nutri.getEmail()) {
			sms.setContenunutri(null);
		}
		smsserv.updateMessage(sms);
	}
	
	
	//supprimer un message envoyer pour tous tous
	@DeleteMapping("/dlMysmset/{idsms}/{idu}")
	public void deletsmset(@PathVariable("idsms")int idsms,@PathVariable("idu")int iduser) {
		Nutritionniste nutri=new Nutritionniste();
		Patient patient=new Patient();
		
		nutri=nt.searchNutritio(iduser);
		patient=pt.searchPatient(iduser);
		
		Message sms=new Message();
		sms=smsserv.searcheMessage(idsms);
		if(patient!=null) {
			if(sms.getExpediteur()==sms.getPatient().getEmail()){
				smsserv.DeletMessage(idsms);	
			}
		}
		else if(nutri!=null) {
			 if(sms.getExpediteur()==sms.getNutritionniste().getEmail()) {
					smsserv.DeletMessage(idsms);
				}
		}
	}
		
	
	@Autowired
	NutritionnisteService ntservice;
	//Enregistrement du nutritioniste
	@PostMapping("/savenutri")
	public ResponseEntity<?>  saveNutri(@RequestParam("nom")String nom,@RequestParam("prenom")String prenom,@RequestParam("email")String email,
			@RequestParam("tel")String tel,@RequestParam("pass")String pass,@RequestParam("specialite")String specialite,
			@RequestParam("photo")MultipartFile photo) {
		String fileName=StringUtils.cleanPath(photo.getOriginalFilename());
		
		if(pservice.Authentification(email, pass)==null) {
			try {
				
				Map uploadResult = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
                String avatarUrl = (String) uploadResult.get("secure_url");

                Nutritionniste nutri = new Nutritionniste();
                nutri.setNom(nom);
                nutri.setPrenom(prenom);
                nutri.setEmail(email);
                nutri.setTel(tel);
                nutri.setPass(pass);
                nutri.setSpecialite(specialite);
                nutri.setAvatarurl(avatarUrl);
                ntservice.AddNutritio(nutri);
			
			} catch (Exception e) {
				// TODO: handle exception
			}

		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("L'utilisateur existe déjà avec ce mail");
			
		}
		
		return ResponseEntity.ok().build();			
	}

	
	//mise à jour du profile  du nutritioniste
		@PostMapping("/updateprofil/{id}")
		public ResponseEntity<?>  updateNutri(@RequestParam("nom")String nom,@RequestParam("prenom")String prenom,
				@RequestParam("tel")String tel,@RequestParam("pass")String pass,@RequestParam("specialite")String specialite,
				@RequestParam("photo")MultipartFile photo,@PathVariable("id")int id) {
			String fileName=StringUtils.cleanPath(photo.getOriginalFilename());
			
			Nutritionniste nutri=ntservice.searchNutritio(id);
			
			if(nutri!=null) {
				try {
					 Map uploadResult = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
		                String avatarUrl = (String) uploadResult.get("secure_url");

		                nutri.setNom(nom);
		                nutri.setPrenom(prenom);
		                nutri.setTel(tel);
		                nutri.setPass(pass);
		                nutri.setSpecialite(specialite);
		                nutri.setAvatarurl(avatarUrl);
		                ntservice.AddNutritio(nutri);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			
			
			
				
		
			
			return ResponseEntity.ok().build();			
		}


	
	
	
	
	
	//recuperer la liste des nutritionistes
	@GetMapping("/allnutri")
	public List<Nutritionniste> findallnutri() {
		return ntservice.findAll();
	}
	
	//recuperer la liste des nutritionniste par spécialité
	@GetMapping("/allnutrispécialite/{specialite}")
	public List<Nutritionniste> findAllBySpecialite(@PathVariable("specialite")String specilite){
		
		return ntservice.searchNutritio(specilite);
	}
	
	//supprimer le compte du nutritionniste
	@DeleteMapping("/delnutri/{id}")
	public void deletNutritionniste(@PathVariable("id")int id) {
		ntservice.deletNutritio(id);
	}
	
	
	
	
	

	@Autowired
	PatientService ptservice;
	//Enregistrement du patient
	@PostMapping("/savepatient")
	public ResponseEntity<?> savePatient(@RequestParam("nom")String nom,@RequestParam("prenom")String prenom,@RequestParam("email")String email,
			@RequestParam("tel")String tel,@RequestParam("pass")String pass,
			@RequestParam("poids")double poids,
			@RequestParam("taille")double taille,
			@RequestParam("photo")MultipartFile photo) { 

		String fileName=StringUtils.cleanPath(photo.getOriginalFilename());
		if(pservice.Authentification(email, pass)==null) {
			try {
				 Map uploadResult = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
	                String avatarUrl = (String) uploadResult.get("secure_url");

	                Patient patient = new Patient();
	                patient.setNom(nom);
	                patient.setPrenom(prenom);
	                patient.setEmail(email);
	                patient.setPass(pass);
	                patient.setPoids(poids);
	                patient.setTaille(taille);
	                patient.setTel(tel);
	                patient.setAvatarurl(avatarUrl);

	                patient.setImc(poids / (taille * taille));
	                if (patient.getImc() < 18.5) {
	                    patient.setTyperegime("sous-poids");
	                } else if (patient.getImc() >= 18.5 && patient.getImc() <= 24.9) {
	                    patient.setTyperegime("normale");
	                } else if (patient.getImc() >= 25 && patient.getImc() <= 29.9) {
	                    patient.setTyperegime("sur-poids");
	                } else {
	                    patient.setTyperegime("obèse");
	                }

	                ptservice.AddPatient(patient);
			} catch (Exception e) {
				// TODO: handle exception
			}

			
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("L'utilisateur existe déjà avec ce mail");
		}
		
		return ResponseEntity.ok().build();
				
	}

	//recuperer la liste des patients
	@GetMapping("/allpatients")
	public List<Patient> findallPatient() {
		return ptservice.findAll();
	}
	
	//recuperer la liste des patients par type de régime
	@GetMapping("/allpatients/{regime}")
	public List<Patient> findAllByRegime(@PathVariable("regime")String regime){
		
		return ptservice.searchPatientregime(regime);
	}
	
	//supprimer le compte du patient
	@DeleteMapping("/delpatients/{id}")
	public void deletPatient(@PathVariable("id")int id) {
		ptservice.deletPatient(id);
	}

	
	@Autowired
	Personneservice pservice;

	//Authentification
	@GetMapping("/authen/{email}/{pass}")
	public ResponseEntity<?> Authentification(@PathVariable("email") String email, @PathVariable("pass") String pass) {
	    Personne personne = pservice.Authentification(email, pass);
	    if (personne == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mot de passe incorrect ou adresse incorrecte");
	    } else {
	        String roleIndice;
	        if (personne instanceof Patient) {
	            roleIndice = "patient";
	        } else if (personne instanceof Nutritionniste) {
	            roleIndice = "nutritionniste";
	        } else {
	            roleIndice = "spécifié";
	        }
	        Map<String, Object> response = new HashMap<>();
	        response.put("personne", personne);
	        response.put("roleIndice", roleIndice);
	        return ResponseEntity.ok(response);
	    }
	}

	
	
	
	
	
	@Autowired
	RecommandationService rcservice;
	@Autowired
	PatientService ps;
	@Autowired
	NutritionnisteService nts;
	@Autowired
	FoodService fs;
	
	//enregistrer une recommandation
	@PostMapping("/sendreco")
	public void sendRecom(@RequestParam("idn")int idn,@RequestParam("idf")int idf,@RequestParam("typeregime")String typeregime) {
		//recherche
		Nutritionniste nutri=new Nutritionniste();
		nutri=nts.searchNutritio(idn);
		
		 System.out.println(idn);
		 System.out.println(idf);
		Food fd=fs.searchFood(idf);
		
		List<Patient> p = ps.searchPatientregime(typeregime);
		int i;
		
		if(p!=null) {
			for(i=0;i<p.size();i++) {
				Recommandation reco=new Recommandation();
				reco.setFood(fd);
				reco.setNutritionniste(nutri);
				reco.setPatient(p.get(i));
				reco.setTyperegime(typeregime);
				rcservice.sendRecom(reco);
			}
			
		}
		
		
		
		
	}
	
//afficher les recommandation d'un patient
	@GetMapping("/myreco/{idp}")
	public List<Recommandation> afficherRecom(@PathVariable("idp")int idp) {
		return rcservice.findAllRecommandationp(idp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}








