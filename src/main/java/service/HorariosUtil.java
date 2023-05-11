package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ResultadoHorario;

public class HorariosUtil {
	public static List<ResultadoHorario> subtrairHorarios(List<String> horariosTrabalho, List<String> horariosMarcacoes) {
	    List<String> horarios = new ArrayList<>();
	    horarios.addAll(horariosTrabalho);
	    horarios.addAll(horariosMarcacoes);
	    horarios.sort(null);

	    List<ResultadoHorario> resultados = new ArrayList<>();

	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	    for (int i = 0; i < horarios.size(); i += 2) {
	        String entradaString = horarios.get(i);
	        String saidaString = horarios.get(i + 1);
	        String tipo = "";

	        try {
	            Date entrada = format.parse(entradaString);
	            Date saida = format.parse(saidaString);

	            if (horariosTrabalho.contains(entradaString) && horariosTrabalho.contains(saidaString)
	                    && horariosMarcacoes.contains(entradaString) && horariosMarcacoes.contains(saidaString)) {
	                continue;
	            } else {
	                boolean horarioEncontrado = false;
	                for (int j = 0; j < horariosTrabalho.size(); j += 2) {
	                    String entradaHorarioString = horariosTrabalho.get(j);
	                    String saidaHorarioString = horariosTrabalho.get(j + 1);

	                    Date entradaHorario = format.parse(entradaHorarioString);
	                    Date saidaHorario = format.parse(saidaHorarioString);

	                    if (entrada.compareTo(entradaHorario) >= 0 && saida.compareTo(saidaHorario) <= 0) {
	                        horarioEncontrado = true;
	                        break;
	                    }
	                }

	                if (horarioEncontrado) {
	                    tipo = "Atraso";
	                } else {
	                    tipo = "Hora-Extra";
	                }

	                ResultadoHorario resultado = new ResultadoHorario(tipo, entradaString, saidaString);
	                resultados.add(resultado);
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }

	    return resultados;
	}
}
