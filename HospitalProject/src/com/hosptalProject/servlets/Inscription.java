package com.hosptalProject.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.hospitalProject.dao.DaoFactory;
import com.hospitalProject.dao.FonctionDao;
import com.hospitalProject.dao.UtilisateurDao;
import com.hospitalProject.dao.DaoException;

import com.hospitalProject.forms.InscriptionForm;
import com.hospitalProject.beans.Fonctions;
import com.hospitalProject.beans.Users;
/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private FonctionDao fonctionDao;
	private UtilisateurDao utilisateurDao;
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.fonctionDao = daoFactory.getFonctionDao();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setAttribute("functions", fonctionDao.listFonction());
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		
        this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		 /* Préparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm(utilisateurDao);
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Users utilisateur = null;
        try {
			utilisateur = form.inscrireUsers( request );
			 request.setAttribute( "users", utilisateur );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
    			request.setAttribute("functions", fonctionDao.listFonction());
    		} catch (DaoException e2) {
    			// TODO Auto-generated catch block
    			request.setAttribute("erreur", "Veuillez remplir tous les champs!");
    		}
			request.setAttribute("erreur", "Veuillez remplir tous les champs");
			this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
		}
		
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( "form", form );
       
		
        if ( form.getErreurs().isEmpty() ) {
        	this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
        }else {
        	try {
    			request.setAttribute("functions", fonctionDao.listFonction());
    			request.setAttribute( "users", utilisateur );
    		} catch (DaoException e) {
    			// TODO Auto-generated catch block
    			request.setAttribute("erreur", e.getMessage());
    		}
        	this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
        
        }
        
	}

}
