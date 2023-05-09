package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarcacaoFeitaDao;
import model.MarcacaoFeita;

/**
 * Servlet implementation class MarcacaoFeitaCreateAndFind
 */
@WebServlet("/MarcacaoFeitaCreateAndFind")
public class MarcacaoFeitaCreateAndFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarcacaoFeitaCreateAndFind() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MarcacaoFeita> marcacoesList = MarcacaoFeitaDao.findAll();
		
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		
		out.print(marcacoesList);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String entrada = request.getParameter("marcacoesEntradaInput");
		String saida = request.getParameter("marcacoesSaidaInput");
		
		MarcacaoFeita marcacao = new MarcacaoFeita(entrada, saida);
		
		MarcacaoFeitaDao.createMarcacao(marcacao);
	}

}
