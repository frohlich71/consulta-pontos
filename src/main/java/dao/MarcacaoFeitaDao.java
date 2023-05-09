package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.PostgresConnection;
import model.MarcacaoFeita;

public class MarcacaoFeitaDao implements MarcacaoFeitaCRUD {

	private static Connection connection = PostgresConnection.createConnection();
	private static String sql;

	public static void createMarcacao(MarcacaoFeita marcacao) {
		sql = "INSERT INTO public.marcacoes_feitas(entrada, saida) VALUES (?, ?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, marcacao.getEntrada());
			preparedStatement.setString(2, marcacao.getSaida());

			preparedStatement.executeUpdate();

			System.out.println("Inserted with success");
		} catch (Exception e) {

			System.out.println("An error ocurred while inserting: " + e.getMessage());

		}

	};

	public static void deleteMarcacao(int id) {
		sql = "DELETE FROM public.marcacoes_feitas WHERE id = ?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			System.out.println("deleted");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	};

	public static List<MarcacaoFeita> findAll() {
		sql = "select * from public.marcacoes_feitas order by id desc;";

		List<MarcacaoFeita> marcacaoFeitaList = new ArrayList<MarcacaoFeita>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				MarcacaoFeita marcacaoFeita = new MarcacaoFeita(resultSet.getInt("id"), resultSet.getString("entrada"),
						resultSet.getString("saida"));

				marcacaoFeitaList.add(marcacaoFeita);
			}

			System.out.println("Returned with sucess");
			return marcacaoFeitaList;

		} catch (Exception e) {
			System.out.println("Error while select from marcacao_feita");
			return null;
		}
	}

	public static MarcacaoFeita findById(int id) {
		return null;
	}

	public static void updateMarcacao(MarcacaoFeita marcacao) {
		sql = "UPDATE marcacoes_feitas SET entrada=?, saida=? WHERE id=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, marcacao.getEntrada());
			preparedStatement.setString(2, marcacao.getSaida());
			preparedStatement.setInt(3, marcacao.getId());

			preparedStatement.executeUpdate();

			System.out.println("updated");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	};
}
