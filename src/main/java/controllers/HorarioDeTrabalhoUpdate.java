package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HorarioDeTrabalhoDao;
import model.HorarioDeTrabalho;

/**
 * Servlet implementation class HorarioDeTrabalhoUpdate
 */
@WebServlet("/HorarioDeTrabalhoUpdate")
public class HorarioDeTrabalhoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HorarioDeTrabalhoUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HorarioDeTrabalho horarioDeTrabalho = new HorarioDeTrabalho(Integer.valueOf(request.getParameter("id")), request.getParameter("entrada"), 
				request.getParameter("saida"));
		
		HorarioDeTrabalhoDao.updateHorarioDeTrabalho(horarioDeTrabalho);
	}

}
