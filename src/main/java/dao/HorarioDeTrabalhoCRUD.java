package dao;

import java.util.List;

import model.HorarioDeTrabalho;

public interface HorarioDeTrabalhoCRUD {

	public static void createHorarioDeTrabalho(HorarioDeTrabalho horario) {};
	public static void deleteHorarioDeTrabalho(int id) {};
	public static List<HorarioDeTrabalho> findAll() {return null;}
	public static HorarioDeTrabalho findById(int id) {return null;}
	public static void updateHorarioDeTrabalho(HorarioDeTrabalho horario) {};
}
