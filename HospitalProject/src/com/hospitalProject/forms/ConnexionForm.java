package com.hospitalProject.forms;

import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hospitalProject.dao.UtilisateurDao;

import com.hospitalProject.beans.Users;

public class ConnexionForm {

	private static final String CHAMP_LOGIN= "login";
	private static final String CHAMP_MDP= "mdp";
	
	private String resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();
	
	private UtilisateurDao utilisateurDao;
	
	public ConnexionForm(UtilisateurDao utilisateurDao) {
		// TODO Auto-generated constructor stub
		this.utilisateurDao = utilisateurDao;
	}

	public String getResultat() {
	    return resultat;
	}

	public Map<String, String> getErreurs() {
	    return erreurs;
	}
	
	
	private String securityPassword(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
	}
	
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
	
	public boolean LoginUsers(HttpServletRequest request) throws Exception {
		boolean valeurRetour= false;
		String login =ConnexionForm.getValeurChamp(request, CHAMP_LOGIN);
		String mdp =ConnexionForm.getValeurChamp(request, CHAMP_MDP);
		
		if(login!=null && mdp!=null) {
		
			Users utilisateurD = utilisateurDao.LoginUtilisateur(login, securityPassword(mdp)) ;
			if (utilisateurD !=null) {
				HttpSession session = request.getSession();
				session.setAttribute("userDetails", utilisateurD);
				int id = utilisateurD.getIdentifantUsers();
				session.setAttribute("infosrdv", utilisateurDao.ProchainRdv(id));
				valeurRetour= true;
			}else {
				valeurRetour= false;
			}
		}else {
			resultat="Veuillez remplir tous les champs";
			valeurRetour= false;
		}
		return valeurRetour;
		}
	
}
