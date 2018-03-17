package com.hospitalProject.forms;

import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import javax.servlet.http.HttpServletRequest;

import com.hospitalProject.dao.UtilisateurDao;
import com.hospitalProject.dao.DaoException;
import com.hospitalProject.beans.Users;

public class InscriptionForm {

	private static final String CHAMP_TITLE= "title";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_FNOM = "firstnom";
	private static final String CHAMP_TEL = "tel";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_MDP = "motdepasse";
	private static final String CHAMP_ADRESSE = "adresse";
	private static final String CHAMP_FONCTION="fonctions" ;
	
	private String resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();
	
	private UtilisateurDao utilisateurDao;
	
	
	public InscriptionForm(UtilisateurDao utilisateurDao) {
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
	

	private void validationEmail( String email ) throws Exception {
	    if ( email != null ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
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

	private void validationMotsDePasse( String motDePasse ) throws Exception {
		if ( motDePasse.length() < 4 ) {
	            throw new Exception( "Les mots de passe doivent contenir au moins 4 caractères." );
	      }
	  
	}

	private void validationNom( String nom ) throws Exception {
	    if ( nom != null && nom.length() < 3 ) {
	        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
	    }
	}

/*
 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
 */
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

	
	public Users inscrireUsers(HttpServletRequest request) throws Exception {
		String title = this.getValeurChamp(request, CHAMP_TITLE);
		String nom = this.getValeurChamp(request, CHAMP_NOM);
		String firstnom =this.getValeurChamp(request, CHAMP_FNOM);
		String tel =this.getValeurChamp(request, CHAMP_TEL);
		String email = this.getValeurChamp(request, CHAMP_EMAIL);
		String login =this.getValeurChamp(request, CHAMP_LOGIN);
		String mdp =this.getValeurChamp(request, CHAMP_MDP);
		String adresse = this.getValeurChamp(request, CHAMP_ADRESSE);
		String fonction = this.getValeurChamp(request,CHAMP_FONCTION);
		int f= Integer.parseInt(fonction);
		Users user = new Users();
		
		user.setTitleUser(title);//titre
		try {
			validationNom(nom);
		}catch(Exception e){
			setErreur(CHAMP_NOM, e.getMessage());
		}
		user.setNomUser(nom);//nom
		try {
			validationNom(firstnom);
		}catch(Exception e){
			setErreur(CHAMP_FNOM, e.getMessage());
		}
		user.setPrenomUser(firstnom);  //prenom
		try {
			validationNom(login);
		}catch(Exception e){
			setErreur(CHAMP_LOGIN, e.getMessage());
		}
		
		user.setLogin(login); //login
		

		try {
			validationMotsDePasse(mdp);
		}catch(Exception e) {
			setErreur(CHAMP_MDP, e.getMessage());
		}
		user.setMdp(securityPassword(mdp));//mot de passe
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
	    user.setEmailUser( email ); //email
	   user.setAdresse(adresse); //adresse
	   
	    user.setTelUser(tel);//tel
	 user.setIdFonctions(f);//fonction

	    if ( erreurs.isEmpty() ) {
	    	utilisateurDao.ajouter(user);
	        resultat = "Succès de l'inscription.";
	    } else {
	        resultat = "Échec de l'inscription.";
	    }
		return user;
		
	}
}
