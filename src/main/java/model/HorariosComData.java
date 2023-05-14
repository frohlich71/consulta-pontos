package model;

import java.util.List;

public class HorariosComData {

	
	List<String> marcacoesComData;
	
	List<String> horariosComData;

	public List<String> getMarcacoesComData() {
		return marcacoesComData;
	}

	public void setMarcacoesComData(List<String> marcacoesComData) {
		this.marcacoesComData = marcacoesComData;
	}

	public List<String> getHorariosComData() {
		return horariosComData;
	}

	public void setHorariosComData(List<String> horariosComData) {
		this.horariosComData = horariosComData;
	}

	public HorariosComData(List<String> marcacoesComData, List<String> horariosComData) {
		super();
		this.marcacoesComData = marcacoesComData;
		this.horariosComData = horariosComData;
	}

	@Override
	public String toString() {
		return "HorariosComData [marcacoesComData=" + marcacoesComData + ", horariosComData=" + horariosComData + "]";
	}
	
	
	
	
}
