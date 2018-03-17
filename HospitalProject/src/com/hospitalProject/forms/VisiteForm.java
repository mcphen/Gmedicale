package com.hospitalProject.forms;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hospitalProject.beans.Visites;
import com.hospitalProject.dao.VisitesDao;
public class VisiteForm {

	private static final String CHAMP_DATERDV = "daterdv";
	private static final String CHAMP_SYMPT = "symptome";
	private static final String CHAMP_ORD = "ordonnance";
	private static final String CHAMP_STATUT ="statut";
	private static final String CHAMP_URL ="url";
	private VisitesDao visitesDao;
	
	
	private String resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();
	
	
	public VisiteForm(VisitesDao visitesDao){
		this.visitesDao = visitesDao;
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
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	
	public boolean ajoutVisite(HttpServletRequest request) throws Exception {
		boolean valeurRetour =false;
		int idu=0;
		Visites vs = new Visites();
		String daterdv=VisiteForm.getValeurChamp(request, CHAMP_DATERDV);
		String symptome = VisiteForm.getValeurChamp(request, CHAMP_SYMPT);
		String ordonnance = VisiteForm.getValeurChamp(request, CHAMP_ORD);
		String statut = VisiteForm.getValeurChamp(request, CHAMP_STATUT);
		String url = VisiteForm.getValeurChamp(request, CHAMP_URL);
		int idConsultation = Integer.parseInt(url);
		int ordre = visitesDao.countOrder(idConsultation)+1;
		int count1 = visitesDao.CountVisites()+1;
		String numCons1 = String.format("%07d", count1 );
		String dateActu = dateFormat.format(date);
		if(daterdv==null && symptome==null && ordonnance==null && statut==null && url==null ) {
			this.resultat = "Veuillez remplir tous les champs";
			valeurRetour=false;
		}else {
			try {
				validationDate(daterdv);
				vs.setNumVisites(numCons1);
				vs.setDateRv(daterdv);
				vs.setDateVisite(dateActu);
				vs.setSymptome(symptome);
				vs.setStatut(statut);
				vs.setOrdonnance(ordonnance);
				vs.setOrdre(ordre);
				vs.setIdConsultation(idConsultation);
				visitesDao.ajouterVisite(vs);
				valeurRetour = true;
			}catch ( Exception e ) {
				
		        setErreur( CHAMP_DATERDV, e.getMessage() );
		        valeurRetour=false;
		    }
		}
		return valeurRetour;
		
	}
	
}
