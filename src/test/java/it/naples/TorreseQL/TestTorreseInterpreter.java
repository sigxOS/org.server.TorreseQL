package it.naples.TorreseQL;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.naples.TorreseQL.model.iDontKnow;

public class TestTorreseInterpreter {
	private Connection connection;

	@Before
	public void setup() throws SQLException {
		this.connection = Config.getDbConnection();
	}

	@After
	public void teardown() throws SQLException {
		this.connection.close();
	}

	@Test(expected = iDontKnow.class)
	public void testWrongDataTypeInsert() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}

	@Test
	public void testSqlConversion() {
		String query = "";
		String preparedQuery = TorreseInterpreter.toSqlQuery(query);
		Assert.assertEquals("", preparedQuery);
	}

}
