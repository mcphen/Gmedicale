package com.hosptalProject.servlets;

import java.io.IOException;
import com.hospitalProject.beans.Visites;
import com.hospitalProject.dao.DaoException;
import com.hospitalProject.dao.DaoFactory;
import com.hospitalProject.dao.VisitesDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hospitalProject.forms.VisiteForm;
/**
 * Servlet implementation class VisiteMedical
 */
@WebServlet("/VisiteMedical")
public class VisiteMedical extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private VisitesDao visitesDao;
	
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.visitesDao = daoFactory.getVisitesDao();
        
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisiteMedical() {
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
		String add =request.getParameter("query");
		int link = Integer.parseInt(link2);
		if(link2!=null) {
			try {
				request.setAttribute("urlparam",link);
				request.setAttribute("editer", link2);
				request.setAttribute("vs",visitesDao.listVisiteByCons(link));
				if(add!=null) {
					this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutVisite.jsp").forward(request, response);
					
				}else {
					this.getServletContext().getRequestDispatcher("/WEB-INF/viewVisite.jsp").forward(request, response);
					
				}
				} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/viewVisite.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		VisiteForm form = new VisiteForm(visitesDao);
		
		try {
			if(form.ajoutVisite(request)==true) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/viewConsultation.jsp").forward(request, response);
			}else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutVisite.jsp").forward(request, response);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
