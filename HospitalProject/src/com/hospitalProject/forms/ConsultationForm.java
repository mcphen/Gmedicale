package com.hospitalProject.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hospitalProject.dao.DaoException;
import com.hospitalProject.dao.ConsultationDao;
import com.hospitalProject.dao.VisitesDao;
import com.hospitalProject.beans.Consultation;
import com.hospitalProject.beans.Visites;
public class ConsultationForm {

	private static final String CHAMP_DATERV = "daterv";
	private static final String CHAMP_SYMPT= "symptome";
	private static final String CHAMP_DIAGN= "diagnos";
	private static final String CHAMP_TR= "traitement";
	private static final String CHAMP_ETAT = "etat";
	private static final String CHAMP_IDP="idpatient";
	private static final String CHAMP_IDU = "idusers";
	private static final String CHAMP_ORD= "ordonnance";
	
	private String resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();
	
	private ConsultationDao consultationDao;
	private VisitesDao visiteDao;
	
	public ConsultationForm(ConsultationDao consultationDao, VisitesDao visiteDao) {
		this.consultationDao = consultationDao;
		this.visiteDao = visiteDao;
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
	 * Methode qui vérifie si la date est correctement saisie
	 */
	
	private void validationDate( String daterv) throws Exception {
	    if ( daterv != null ) {
	        if ( !daterv.matches("\\d{4}-\\d{2}-\\d{2}" ) ) {
	            throw new Exception( "La date de naissance doit s'enregistrer dans le format AAAA-MM-JJ." );
	        }
	    } else {
	    	 daterv = null ;
	    }
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
	
	public boolean updateConsultation(HttpServletRequest request) {
		boolean valeurRetour=true;
		String daterv=ConsultationForm.getValeurChamp(request, CHAMP_DATERV);
		
		String diagnostic = ConsultationForm.getValeurChamp(request, CHAMP_DIAGN);
		String traitement = ConsultationForm.getValeurChamp(request, CHAMP_TR);
		String etatTr = ConsultationForm.getValeurChamp(request, CHAMP_ETAT);
		String iduser = ConsultationForm.getValeurChamp(request,CHAMP_IDU);
		int idu= Integer.parseInt(iduser);
	
		
		try {
			Consultation consultation = new Consultation();
			consultation.setDiagnostique(diagnostic);
			consultation.setEtatTraitement(etatTr);
			
			consultation.setIdUserCons(idu);
			
			consultation.setTraitement(traitement);
			
			consultation.setDateRv(daterv);
			consultationDao.modificationConsultation(consultation);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valeurRetour;
		
		
		
		
	}
	
	public boolean ajoutConsultation(HttpServletRequest request) throws Exception{
		boolean valeurRetour =false;
		int idu=0;
		String daterv=ConsultationForm.getValeurChamp(request, CHAMP_DATERV);
		String symptome=ConsultationForm.getValeurChamp(request, CHAMP_SYMPT);
		String diagnostic = ConsultationForm.getValeurChamp(request, CHAMP_DIAGN);
		String traitement = ConsultationForm.getValeurChamp(request, CHAMP_TR);
		String etatTr = ConsultationForm.getValeurChamp(request, CHAMP_ETAT);
		String idpatient = ConsultationForm.getValeurChamp(request,CHAMP_IDP);
		String ordonnance = ConsultationForm.getValeurChamp(request,CHAMP_ORD);
		
		
		
		
		String iduser = ConsultationForm.getValeurChamp(request,CHAMP_IDU);
		
		idu =Integer.parseInt(iduser);
		int count1 = visiteDao.CountVisites()+1;
		int count = consultationDao.CountPatient()+1;
		String numCons = String.format("%07d", count );
		String numCons1 = String.format("%07d", count1 );
		if(symptome!=null && diagnostic!=null && traitement!=null && etatTr!=null && idpatient!=null && ordonnance!=null) {
			try {
				validationDate(daterv);
			}catch ( Exception e ) {
		        setErreur( CHAMP_DATERV, e.getMessage() );
		    }
			String statut = "DT";
			Consultation consultation = new Consultation();
			Visites visite = new Visites();
			
			 if ( erreurs.isEmpty() ) {
				 
						consultation.setNumConsultation(numCons);
						consultation.setDiagnostique(diagnostic);
						consultation.setEtatTraitement(etatTr);
						consultation.setIdPatientCons(idpatient);
						consultation.setIdUserCons(idu);
						
						consultation.setTraitement(traitement);
						visite.setIdConsultation(count);
						visite.setDateRv(daterv);
						visite.setNumVisites(numCons1);
						visite.setOrdonnance(ordonnance);
						visite.setStatut(statut);
						visite.setSymptome(symptome);
						consultationDao.ajouterConsultation(consultation, visite);
						resultat = "Consutation enregistrée avec succès";
						valeurRetour = true;
					
			    } 
		}else {
		    	valeurRetour = false;
		    	resultat = "Veuillez remplir tous les champs!";
		    }
		
		return valeurRetour;
	}
	/*
	public void recherchePatient(HttpServletRequest request) throws DaoException {
		String search = this.getValeurChamp(request, CHAMP_SEARCH);
		String message="Aucun resultat trouvé";
		if(search!=null) {
			if( patientDao.SearchPatient(search) !=null) {
				request.setAttribute("sPatient", patientDao.SearchPatient(search));
			}else {
				setErreur(CHAMP_SEARCH, message);
			}
		}
		
	}*/
	
}
