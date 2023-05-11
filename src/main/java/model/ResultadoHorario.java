package model;

public class ResultadoHorario {
    private String tipo;
    private String entrada;
    private String saida;

    public ResultadoHorario(String tipo, String entrada, String saida) {
        this.tipo = tipo;
        this.entrada = entrada;
        this.saida = saida;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEntrada() {
        return entrada;
    }

    public String getSaida() {
        return saida;
    }

	@Override
	public String toString() {
		return "ResultadoHorario [tipo=" + tipo + ", entrada=" + entrada + ", saida=" + saida + "]";
	}
    
    
}
