package com.hosptalProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.hospitalProject.dao.DaoFactory;

import com.hospitalProject.dao.FonctionDao;

import com.hospitalProject.forms.PathologieForm;

/**
 * Servlet implementation class Pathologie
 */
@WebServlet("/Pathologie")
public class Pathologie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private FonctionDao fonctionDao;
	
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.fonctionDao = daoFactory.getFonctionDao();
       
    }
    public Pathologie() {
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
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutPathologie.jsp").forward(request, response);
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/pathologie.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PathologieForm form = new PathologieForm(fonctionDao);
		
		if(form.InserPathologie(request)==true) {
			request.setAttribute("success", "Pathologie ajouté avec succèss");
			this.getServletContext().getRequestDispatcher("/Administration").forward(request, response);
			
		}else {
			request.setAttribute("erreur", "Veuillez remplir le formulaire");
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutPathologie.jsp").forward(request, response);
			
		}
		
		
	}

}
