package com.hosptalProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospitalProject.dao.VisitesDao;
import com.hospitalProject.dao.ConsultationDao;
import com.hospitalProject.dao.DaoException;
import com.hospitalProject.dao.DaoFactory;
import com.hospitalProject.dao.FonctionDao;

import com.hospitalProject.forms.ConsultationForm;

/**
 * Servlet implementation class EditerVisite
 */
@WebServlet("/EditerVisite")
public class EditerVisite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DaoFactory daoFactory;
	private FonctionDao fonctionDao;
	private VisitesDao visiteDao;
	private ConsultationDao consultationDao;
	
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.fonctionDao = daoFactory.getFonctionDao();
        this.visiteDao = daoFactory.getVisitesDao();
        this.consultationDao = daoFactory.getConsultationDao();
        
    }
    public EditerVisite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String link = request.getParameter("q");
		if(link!=null) {
			int idConsultation= Integer.parseInt(link);
			try {
				request.setAttribute("modif", visiteDao.listConsultation(idConsultation));
				request.setAttribute("functions", fonctionDao.listPathologie());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/editerVisite.jsp").forward(request, response);
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
			if(form.updateConsultation(request)==true) {
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
