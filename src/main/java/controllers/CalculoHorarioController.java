package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HorariosComData;
import model.ResultadoHorario;
import service.HorariosUtil;

/**
 * Servlet implementation class CalculoHorario
 */
@WebServlet("/CalculoHorario")
public class CalculoHorarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculoHorarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String horariosResponse = request.getParameter("horarios");
			String marcacoesResponse = request.getParameter("marcacoes");
			
			String elementosHorarios = horariosResponse.replace("[", "").replace("]", "");
			String elementosMarcacoes = marcacoesResponse.replace("[", "").replace("]", "");
			
			
			String[] horariosArray = elementosHorarios.split(",");
			String[] marcacoesArray = elementosMarcacoes.split(",");
			
			List<String> horarios = Arrays.asList(horariosArray);
			List<String> marcacoes = Arrays.asList(marcacoesArray);
			
			
			PrintWriter out = response.getWriter();
			
			
			HorariosComData horariosComData = HorariosUtil.adicionarDataAosHorarios(horarios, marcacoes);
			
			List<ResultadoHorario> resultHorarios = HorariosUtil.subtrairHorarios(horariosComData.getHorariosComData(), horariosComData.getMarcacoesComData());
			
			out.print(resultHorarios);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
