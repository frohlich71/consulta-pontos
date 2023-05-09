package dao;

import java.util.List;

import model.MarcacaoFeita;


public interface MarcacaoFeitaCRUD {
	public static void createMarcacao(MarcacaoFeita marcacao) {};
	public static void deleteMarcacao(int id) {};
	public static List<MarcacaoFeita> findAll() {return null;}
	public static MarcacaoFeita findById(int id) {return null;}
	public static void updateHorarioDeTrabalho(MarcacaoFeita marcacao) {};
}
