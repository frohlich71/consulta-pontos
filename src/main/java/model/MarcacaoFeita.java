package model;

public class MarcacaoFeita {
	private int id;
	private String entrada;
	private String saida;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEntrada() {
		return entrada;
	}
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	public String getSaida() {
		return saida;
	}
	public void setSaida(String saida) {
		this.saida = saida;
	}
	
	public MarcacaoFeita(int id, String entrada, String saida) {
		this.id = id;
		this.entrada = entrada;
		this.saida = saida;
	}
	
	public MarcacaoFeita(String entrada, String saida) {
		this.entrada = entrada;
		this.saida = saida;
	}
	@Override
	public String toString() {
		return "MarcacaoFeita [id=" + id + ", entrada=" + entrada + ", saida=" + saida + "]";
	}
	
	
}
