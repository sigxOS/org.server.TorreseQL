package it.naples.TorreseQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.naples.TorreseQL.model.QueryResult;

public class TestSelect {
	private final static String[] data = { "" };

	private Connection connection;

	@Before
	public void setup() throws SQLException {
		this.connection = Config.getDbConnection();
	}

	@After
	public void teardown() throws SQLException {
		this.connection.close();
	}

	@Test
	public void testSelectAsterisk() throws SQLException {
		TorreseInterpreter gsi = new TorreseInterpreter(connection);
		QueryResult result = gsi.execute("");
		ResultSet resultSet = result.getResultSet();

		int expectedId = 1;
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String data = resultSet.getString(2);

			Assert.assertEquals(
					expectedId,
					id
			);

			Assert.assertEquals(
					TestSelect.data[expectedId - 1],
					data
			);
			expectedId++;
		}
		Assert.assertEquals(5, expectedId);
	}

	@Test
	public void testSelectWhere() throws SQLException {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		QueryResult result = interpreter.execute("");
		ResultSet resultSet = result.getResultSet();

		int expectedId = 1;

		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String city = resultSet.getString(2);

			Assert.assertEquals(expectedId, id);
			Assert.assertEquals(data[expectedId - 1], city);
			expectedId++;
		}

		Assert.assertEquals(3, expectedId);
	}

	@Test
	public void testSelectWhereAnd() throws SQLException {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		QueryResult result = interpreter.execute("");
		ResultSet resultSet = result.getResultSet();

		int counter = 1;
		while (resultSet.next()) {
			String fD = resultSet.getString(1);
			String sD = resultSet.getString(2);
			Assert.assertEquals("", fD);
			Assert.assertEquals("", sD);
			counter++;
		}

		Assert.assertEquals(2, counter);
	}

	@Test
	public void testSelectWhereOr() throws SQLException {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		QueryResult result = interpreter.execute("");
		ResultSet resultSet = result.getResultSet();

		int counter = 1;
		String[] firstField = {
				"",
				""
		};
		String[] secondField = {
				"",
				""
		};

		while (resultSet.next()) {
			String fF = resultSet.getString(1);
			String sF = resultSet.getString(2);

			Assert.assertEquals(firstField[counter - 1], fF);
			Assert.assertEquals(secondField[counter - 1], sF);
			counter++;
		}

		Assert.assertEquals(3, counter);
	}

	@Test
	public void testSelectJoin() throws SQLException {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		QueryResult result = interpreter.execute(
				"");
		ResultSet resultSet = result.getResultSet();
		int counter = 1;
		String[] fF = {
				"",
				""
		};

		String[] sF = {
				"",
				""
		};

		while (resultSet.next()) {
			String fD = resultSet.getString(1);
			String sD = resultSet.getString(2);

			Assert.assertEquals(fF[counter - 1], fD);
			Assert.assertEquals(sF[counter - 1], sD);
			counter++;
		}

		Assert.assertEquals(3, counter);
	}

	@Test
	public void testSelectWhereIsNot() throws SQLException {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		QueryResult result = interpreter.execute("");
		ResultSet resultSet = result.getResultSet();

		int counter = 1;
		String[] fF = {
				"",
				"",
				""
		};

		while (resultSet.next()) {
			String name = resultSet.getString(1);
			Assert.assertEquals(fF[counter - 1], name);

			counter++;
		}

		Assert.assertEquals(4, counter);
	}

}