package it.naples.TorreseQL;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryResult;

public class TorreseShell {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(
				System.in,
                StandardCharsets.UTF_8
		);

		System.out.print("Inserisci la stringa JDBC: ");
		String URL = scanner.nextLine();
		Connection connection = DriverManager.getConnection(URL);
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);

		System.out.println("Database: Connesso");
		while (true) {
			try {
				System.out.print("> ");
				String query = scanner.nextLine();
				System.out.println(query);

				QueryResult result = interpreter.execute(query);
				if (result.getResultSet() != null)
					printSelectResult(result.getResultSet());

				if (result.getAffectedRows() != null)
					System.out.println("Righe coinvolte: " + result.getAffectedRows());

				if (result.getResultSet() == null && result.getAffectedRows() == null)
					System.out.println("OK");

			} catch (iDontKnow | SQLException exception) {
				System.err.println("Errore: " + exception.getMessage());
			}
		}
	}

	private static void printSelectResult(ResultSet result) throws SQLException {
		ResultSetMetaData resultMetaData = result.getMetaData();

		int columnsNumber = resultMetaData.getColumnCount();

		for (int i = 1; i <= columnsNumber; i++)
			System.out.print(resultMetaData.getColumnName(i) + " | ");

		System.out.println();

		while (result.next()) {
			for (int i = 1; i <= columnsNumber; i++)
				System.out.print(result.getString(i) + " | ");

			System.out.println();
		}
	}
}