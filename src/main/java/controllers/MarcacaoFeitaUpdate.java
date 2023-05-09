package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HorarioDeTrabalhoDao;
import dao.MarcacaoFeitaDao;
import model.HorarioDeTrabalho;
import model.MarcacaoFeita;

/**
 * Servlet implementation class MarcacaoFeitaUpdate
 */
@WebServlet("/MarcacaoFeitaUpdate")
public class MarcacaoFeitaUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarcacaoFeitaUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MarcacaoFeita marcacaoFeita = new MarcacaoFeita(
				Integer.valueOf(request.getParameter("id")),
				request.getParameter("entrada"), 
				request.getParameter("saida"));
		
		MarcacaoFeitaDao.updateMarcacao(marcacaoFeita);
	}

}
