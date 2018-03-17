package com.hosptalProject.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospitalProject.beans.Patients;
import com.hospitalProject.dao.DaoException;
import com.hospitalProject.dao.DaoFactory;
import com.hospitalProject.dao.FonctionDao;
import com.hospitalProject.dao.PatientDao;
import com.hospitalProject.dao.VisitesDao;
import com.hospitalProject.dao.ConsultationDao;
import com.hospitalProject.forms.ConsultationForm;

/**
 * Servlet implementation class Consultation
 */
@WebServlet("/Consultation")
public class Consultation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private PatientDao patientDao;
	private ConsultationDao consultationDao;
	private VisitesDao visiteDao;
	private FonctionDao fonctionDao;
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.patientDao = daoFactory.getPatientDao();
        this.consultationDao = daoFactory.getConsultationDao();
        this.visiteDao = daoFactory.getVisitesDao();
        this.fonctionDao = daoFactory.getFonctionDao();
        
    }
	
    public Consultation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String link2=request.getParameter("q");
		if(request.getParameter("search")==null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/viewConsultation.jsp").forward(request, response);
		}else {
			try {
				
				request.setAttribute("res", patientDao.SearchPatient(request.getParameter("search")));
				request.setAttribute("resAll", patientDao.SearchAllPatient(request.getParameter("search")));
				if(link2!=null) {
					request.setAttribute("idpatient", link2);
					request.setAttribute("functions", fonctionDao.listPathologie());
					this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutConsultation.jsp").forward(request, response);
					
				}else {
					this.getServletContext().getRequestDispatcher("/WEB-INF/viewConsultation.jsp").forward(request, response);
				}
				
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		ConsultationForm form = new ConsultationForm(consultationDao, visiteDao);
		
		try {
			if(form.ajoutConsultation(request)==true) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/viewConsultation.jsp").forward(request, response);
			}else {
				 request.setAttribute( "form", form );
				this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutConsultation.jsp").forward(request, response);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
