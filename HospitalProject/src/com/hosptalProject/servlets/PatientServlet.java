package com.hosptalProject.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospitalProject.forms.PatientForm;
import com.hospitalProject.dao.DaoFactory;
import com.hospitalProject.dao.PatientDao;
import com.hospitalProject.beans.Patients;
import com.hospitalProject.dao.DaoException;
/**
 * Servlet implementation class PatientServlet
 */
@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private PatientDao patientDao;
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.patientDao = daoFactory.getPatientDao();
        
    }
	
    public PatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			request.setAttribute("listePatient", patientDao.ListPatient(request.getParameter("search")));
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		String link1=request.getParameter("q");
		String link2=request.getParameter("p");
		if(link1!=null && link1.equals("ajoutPatient")){
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutPatient.jsp").forward(request, response);
		}else if(link2!=null){
			try {
				request.setAttribute("story", patientDao.storyPatient(link2));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/storyPatient.jsp").forward(request, response);
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/viewPatient.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	/*	int count =10;
		 String valeur = request.getParameter("dateN");
		 String valeur1 = request.getParameter("nom");
		 String[] tab= valeur.split("/");
		 String sub1 = valeur1.substring(0, 3);
		 String formatValue = String.format("%04d", count );
		 String sub= tab[0].substring(2, 4);
		 String res1 = sub1+sub+formatValue;
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String dateActu = dateFormat.format(date);
		 request.setAttribute("affiche", valeur);
		 request.setAttribute("affichesub", dateActu);
		 request.setAttribute("affichesub1", sub1);
		 request.setAttribute("affichesub2", res1);
		 this.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
	*/
		PatientForm form = new PatientForm(patientDao);
		
		Patients patient = null;
		
		try {
			patient = form.inscrirePatients(request);
			request.setAttribute( "form", form );
			if ( form.getErreurs().isEmpty() ) {
	        	this.getServletContext().getRequestDispatcher("/WEB-INF/viewPatient.jsp").forward(request, response);
	        }else {
	        	request.setAttribute( "users", patient );
	        	this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutPatient.jsp").forward(request, response);
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		
		
        /* Stockage du formulaire et du bean dans l'objet request */
        
       
		
        
		//
        
		
		
	}

}
