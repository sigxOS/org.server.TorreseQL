package it.naples.TorreseQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.naples.TorreseQL.model.QueryResult;

public class TestTransactions {
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
	public void testTransactionRollback() throws SQLException {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);

		interpreter.execute("");
		interpreter.execute("");

		QueryResult result = interpreter.execute("");
		ResultSet resultSet = result.getResultSet();

		int expectedCount = 1;

		while (resultSet.next())
			expectedCount++;

		Assert.assertEquals(expectedCount, 2);

		interpreter.execute("");
		result = interpreter.execute("");
		resultSet = result.getResultSet();
		expectedCount = 1;

		while (resultSet.next())
			expectedCount++;

		Assert.assertEquals(expectedCount, 1);
	}
	
	@Test
	public void testTransactionCommit() throws SQLException {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);

		interpreter.execute("");
		interpreter.execute("");

		QueryResult result = interpreter.execute("");

		ResultSet resultSet = result.getResultSet();

		int expectedCount = 1;

		while (resultSet.next())
			expectedCount++;

		Assert.assertEquals(expectedCount, 2);

		interpreter.execute("");
		result = interpreter.execute("");
		resultSet = result.getResultSet();
		expectedCount = 1;

		while (resultSet.next())
			expectedCount++;

		Assert.assertEquals(expectedCount, 2);
	}
}