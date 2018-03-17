package com.hospitalProject.forms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hospitalProject.dao.PatientDao;

import com.hospitalProject.dao.DaoException;
import com.hospitalProject.beans.Patients;


public class PatientForm {

	private static final String CHAMP_TITRE = "titre";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_PROF = "profession";
	private static final String CHAMP_ADR = "adresse";
	private static final String CHAMP_TEL = "tel";
	private static final String CHAMP_EMAIL= "email";
	private static final String CHAMP_DATEN = "dateN";
	
	private String resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();
	
	private PatientDao patientDao;
	
	public PatientForm(PatientDao patientDao) {
		this.patientDao = patientDao;
	}
	
	public String getResultat() {
	    return resultat;
	}

	public Map<String, String> getErreurs() {
	    return erreurs;
	}
	
	private void setErreur( String champ, String message ) {
	    erreurs.put( champ, message );
	}


	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
	Date date = new Date();
	
	private void validationEmail( String email ) throws Exception {
	    if ( email != null ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}
	
	private void validationDate( String dateN ) throws Exception {
	    if ( dateN != null ) {
	        if ( !dateN.matches("\\d{4}-\\d{2}-\\d{2}" ) ) {
	            throw new Exception( "La date de naissance doit s'enregistrer dans le format AAAA-MM-JJ." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}
	
	private void validationTel(String telephone) throws Exception {
		  if ( telephone != null ) {
	            if ( !telephone.matches( "^\\d+$" ) ) {
	                throw new Exception( "Le numéro de téléphone doit uniquement contenir des chiffres." );
	            } else if ( telephone.length() < 4 ) {
	                throw new Exception( "Le numéro de téléphone doit contenir au moins 4 chiffres." );
	            }
	        } else {
	            throw new Exception( "Merci d'entrer un numéro de téléphone." );
	        }
	}
	
	private void validationNom( String nom ) throws Exception {
	    if ( nom != null && nom.length() < 3 ) {
	        throw new Exception( "Il doit contenir au moins 3 caractères." );
	    }
	}
	
	private void validationAdr( String adresse ) throws Exception {
	    if ( adresse != null && adresse.length() < 10 ) {
	        throw new Exception( "Il doit contenir au moins 10 caractères." );
	    }
	}
	
	public Patients inscrirePatients(HttpServletRequest request) throws Exception {
		
		String titre = PatientForm.getValeurChamp(request, CHAMP_TITRE);
		String nom = PatientForm.getValeurChamp(request, CHAMP_NOM);
		String prenom= PatientForm.getValeurChamp(request, CHAMP_PRENOM);
		String profession= PatientForm.getValeurChamp(request, CHAMP_PROF);
		String adresse = PatientForm.getValeurChamp(request, CHAMP_ADR);
		String tel = PatientForm.getValeurChamp(request, CHAMP_TEL);
		String email = PatientForm.getValeurChamp(request, CHAMP_EMAIL);
		String dateN= PatientForm.getValeurChamp(request, CHAMP_DATEN);
		
	
		    
Patients patient = new Patients();
			
			
			try {
				validationNom(nom);
			}catch(Exception e){
				setErreur(CHAMP_NOM, e.getMessage());
			}
			
			
			try {
				validationNom(prenom);
			}catch(Exception e){
				setErreur(CHAMP_PRENOM, e.getMessage());
			}
			
			
			try {
				validationNom(profession);
			}catch(Exception e){
				setErreur(CHAMP_PROF, e.getMessage());
			}
			
			
			try {
				validationAdr(adresse);
			}catch(Exception e){
				setErreur(CHAMP_ADR, e.getMessage());
			}
			
			
			
			try {
		        validationEmail( email );
		    } catch ( Exception e ) {
		        setErreur( CHAMP_EMAIL, e.getMessage() );
		    }
			
			try {
				validationTel(tel);
			}catch ( Exception e ) {
		        setErreur( CHAMP_TEL, e.getMessage() );
		    }
			
			try {
				validationDate(dateN);
			}catch ( Exception e ) {
		        setErreur( CHAMP_DATEN, e.getMessage() );
		    }
			
			patient.setTitrePatient(titre);
		    patient.setDateNaissance(dateN);
			
			patient.setTelPatient(tel);
			patient.setEmailPatient(email);
			patient.setAdressePatient(adresse);
			patient.setProfessionPatient(profession);
			patient.setPrenomPatient(prenom);
			nom = nom.toUpperCase();
			patient.setNomPatient(nom);
			if ( erreurs.isEmpty() ) {

		    	
		    	String dateActu = dateFormat.format(date);
				String year = dateFormat1.format(date);
				String dateActuS= dateActu.substring(2, 4);
				String nomSub =nom.substring(0, 3);
				int count = patientDao.CountPatient(nom,year)+1;
			    String formatValue = String.format("%04d", count );
			    String codePatient=nomSub+dateActuS+formatValue;
			    
			    patient.setCodePatient(codePatient);
				patient.setDateEnregist(dateActu);
		    	patientDao.ajouterPatient(patient);
		        resultat = "Succès de l'insertion.";
		      
		    } else {
		    	
		    	
		        resultat = "Échec de l'insertion.";
		    }
	    	
	   
	    return patient;
	}
}
