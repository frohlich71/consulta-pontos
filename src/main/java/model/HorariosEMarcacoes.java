package model;

import java.util.List;

public class HorariosEMarcacoes {
    private List<String> horariosComDatas;
    private List<String> marcacoesComDatas;

    public HorariosEMarcacoes(List<String> horariosComDatas, List<String> marcacoesComDatas) {
        this.horariosComDatas = horariosComDatas;
        this.marcacoesComDatas = marcacoesComDatas;
    }

    public List<String> getHorariosComDatas() {
        return horariosComDatas;
    }

    public List<String> getMarcacoesComDatas() {
        return marcacoesComDatas;
    }

	@Override
	public String toString() {
		return "HorariosEMarcacoes [horariosComDatas= " + horariosComDatas + ", marcacoesComDatas= " + marcacoesComDatas
				+ "]";
	}
    
    
}
