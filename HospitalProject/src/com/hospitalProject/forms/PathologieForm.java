package com.hospitalProject.forms;

import javax.servlet.http.HttpServletRequest;

import com.hospitalProject.dao.FonctionDao;
import com.hospitalProject.dao.DaoException;
import com.hospitalProject.beans.Fonctions;
public class PathologieForm {

	private static final String CHAMP_NOM = "nom";
	
	private FonctionDao fonctionDao;
	
	public PathologieForm(FonctionDao fonctionDao){
		this.fonctionDao = fonctionDao;
	}
	
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
	
	public boolean InserPathologie(HttpServletRequest request) {
		boolean valeurRetour =false;
		String nom = this.getValeurChamp(request, CHAMP_NOM);
		
		Fonctions fonctions = new Fonctions();
		
		if(nom!=null) {
			fonctions.setLibellePathologie(nom);
			try {
				fonctionDao.ajouterPathologie(fonctions);
				
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			valeurRetour = true;
			
		}else {
			valeurRetour = false;
		}
		
		
		return valeurRetour;
	}
	
	
}
