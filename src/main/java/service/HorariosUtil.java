package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.HorariosComData;
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
            
            
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    
    for (int i = 0; i < resultados.size(); i++) {
    	ResultadoHorario resultado = resultados.get(i);
    	
    	if (resultado.getEntrada().compareTo(resultado.getSaida()) == 0){
    		resultados.remove(i);
    		i--;
    	}
    	
    }
    
    return resultados;
}
	
	
	
	public static HorariosComData adicionarDataAosHorarios(List<String> horariosTrabalho, List<String> marcacoes) throws ParseException {
		List<String> horariosComData = new ArrayList<>();
	    List<String> marcacoesComData = new ArrayList<>();

	    Calendar calendar_atual = Calendar.getInstance();
	    
	    Calendar calendar_proximo_dia = Calendar.getInstance();
		
	    calendar_atual.add(Calendar.DAY_OF_MONTH, 0);  
	    calendar_proximo_dia.add(Calendar.DAY_OF_MONTH, 1);
	    
	    String dataAtual;
	    String proximoDia;
	    
	    
	    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
	    
	    dataAtual = formatoData.format(calendar_atual.getTime());
	    proximoDia = formatoData.format(calendar_proximo_dia.getTime());
	        
	    boolean isVirada = false;
	    Date entradaVirada = new Date();
	    Date saidaVirada = new Date();

	    
	    
	    for (int i = 0; i < horariosTrabalho.size(); i+= 2) {
	    	String entradaString = horariosTrabalho.get(i);
	    	String saidaString = horariosTrabalho.get(i + 1);
	    	
	    	if (saidaString.compareTo(entradaString) < 0 || isVirada) {
	    		isVirada = true;
	    		
	    		entradaVirada = formatoHora.parse(entradaString);
	    		saidaVirada = formatoHora.parse(saidaString);
	    	}
	    }
	    
	    
	    if (isVirada) {
	    	
	    	boolean outroDiaHorario = false;
	    	boolean outroDiaMarca = false;
	    	
	    	for (String horario: horariosTrabalho) {
	    		
	    		Date horarioEmData = formatoHora.parse(horario);
	    		
	    		if (horarioEmData.before(formatoHora.parse("23:59")) && horarioEmData.after(saidaVirada) && !outroDiaHorario)
	    		{
	    			String horarioComData = dataAtual + " " + horario;
	    			
			    	horariosComData.add(horarioComData);
			    	
	    		} else {
	    			
	    			outroDiaHorario = true;
	    			
	    			String horarioComData = proximoDia + " " + horario;
	    			horariosComData.add(horarioComData);

	    		}
	    	}
	    	
	    	
	    	
	    	for (String marcacao : marcacoes) {
	    		
	    		Date marcacaoEmData = formatoHora.parse(marcacao);
	    		
	    		if (marcacaoEmData.before(formatoHora.parse("23:59")) && marcacaoEmData.after(saidaVirada) && !outroDiaMarca)
	    		{
	    			String marcacaoComData = dataAtual + " " + marcacao;
	    			
			    	marcacoesComData.add(marcacaoComData);
			    	
	    		} else {
	    			outroDiaMarca = true;
	    			
	    			String marcacaoComData = proximoDia + " " + marcacao;
	    			marcacoesComData.add(marcacaoComData);
	    			
	    		}
	    	}
	    	
	    } else {
	    	for (String horario : horariosTrabalho) {
		    	String horarioComData = dataAtual + " " + horario;
		    	horariosComData.add(horarioComData);
		    }

		    for (String marcacao : marcacoes) {
		    	String marcacaoComData = dataAtual + " " + marcacao;
		    	marcacoesComData.add(marcacaoComData);
		    }
	    }

	    return new HorariosComData(marcacoesComData, horariosComData);
	}
	
}	
