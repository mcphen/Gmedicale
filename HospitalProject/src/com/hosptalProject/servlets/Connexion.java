package com.hosptalProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hospitalProject.dao.DaoFactory;

import com.hospitalProject.dao.UtilisateurDao;


import com.hospitalProject.forms.ConnexionForm;


/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	HttpSession session;
	private UtilisateurDao utilisateurDao;
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
      
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String link1=request.getParameter("auth");
		String link2=request.getParameter("q");
		request.setAttribute("titreSite", "Accueil");
		if(link1!=null && link1.equals("deconnexion")){
			session=request.getSession();  
            session.invalidate();  
            session=null;
		    this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}else if(link2!=null && link2.equals("home")){
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
		if(session==null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		 
		ConnexionForm form = new ConnexionForm(utilisateurDao);
		try {
			if(form.LoginUsers(request)==true) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			}else {
				request.setAttribute("erreur", "Login ou mot de passe incorrecte, veuillez réessayer!");
				this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
