package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HorarioDeTrabalhoDao;
import dao.MarcacaoFeitaDao;

/**
 * Servlet implementation class MarcacaoFeitaDelete
 */
@WebServlet("/MarcacaoFeitaDelete")
public class MarcacaoFeitaDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarcacaoFeitaDelete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int marcacaoId = Integer.parseInt(request.getParameter("id"));

		MarcacaoFeitaDao.deleteMarcacao(marcacaoId);
	}

}
