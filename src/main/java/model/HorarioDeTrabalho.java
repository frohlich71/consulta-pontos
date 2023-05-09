package model;

public class HorarioDeTrabalho {
	
	private int id;
	private String entrada;
	private String saida;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public HorarioDeTrabalho(String entrada, String saida) {
		this.entrada = entrada;
		this.saida = saida;
	}
	
	public HorarioDeTrabalho(int id,String entrada, String saida) {
		this.id = id;
		this.entrada = entrada;
		this.saida = saida;
	}
	
	@Override
	public String toString() {
		return "HorarioDeTrabalho [id=" + id + ", entrada=" + entrada + ", saida=" + saida + "]";
	}
	
	
}
