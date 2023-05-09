package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.PostgresConnection;
import model.HorarioDeTrabalho;

public class HorarioDeTrabalhoDao implements HorarioDeTrabalhoCRUD {

	private static Connection connection = PostgresConnection.createConnection();
	private static String sql;

	public static void createHorarioDeTrabalho(HorarioDeTrabalho horario) {
		sql = "INSERT INTO public.horario_trabalho(entrada, saida) VALUES (?, ?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, horario.getEntrada());
			preparedStatement.setString(2, horario.getSaida());

			preparedStatement.executeUpdate();

			System.out.println("Inserted with success");
		} catch (Exception e) {

			System.out.println("An error ocurred while inserting: " + e.getMessage());

		}

	};

	public static void deleteHorarioDeTrabalho(int id) {

		sql = "DELETE FROM public.horario_trabalho WHERE id = ?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			System.out.println("deleted");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	};

	public static List<HorarioDeTrabalho> findAll() {
		sql = "select * from public.horario_trabalho ht order by id desc limit 3;";

		List<HorarioDeTrabalho> horarioDeTrabalhoList = new ArrayList<HorarioDeTrabalho>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				HorarioDeTrabalho horarioDeTrabalho = new HorarioDeTrabalho(resultSet.getInt("id"),
						resultSet.getString("entrada"), resultSet.getString("saida"));

				horarioDeTrabalhoList.add(horarioDeTrabalho);
			}

			System.out.println("Returned with sucess");
			return horarioDeTrabalhoList;

		} catch (Exception e) {
			System.out.println("Error while select from horario_trabalho");
			return null;
		}
	}

	public static HorarioDeTrabalho findById(int id) {
		return null;
	}

	public static void updateHorarioDeTrabalho(HorarioDeTrabalho horario) {

		sql = "UPDATE horario_trabalho SET entrada=?, saida=? WHERE id=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, horario.getEntrada());
			preparedStatement.setString(2, horario.getSaida());
			preparedStatement.setInt(3, horario.getId());

			preparedStatement.executeUpdate();

			System.out.println("updated");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	};
}
