package it.naples.TorreseQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.naples.TorreseQL.model.QueryResult;

public class TestDelete {
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
	public void testDeleteAll() throws SQLException {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		QueryResult result = interpreter.execute("");
		Assert.assertEquals((Integer) 4, result.getAffectedRows());
		
		result = interpreter.execute("");
		ResultSet resultSet = result.getResultSet();
		int counter = 0;
		while (resultSet.next()) {
			counter++;
		}
		Assert.assertEquals(0, counter);
	}
	
	@Test
	public void testDeleteWithWhere() throws SQLException {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		QueryResult result = interpreter.execute("");
		Assert.assertEquals((Integer) 3, result.getAffectedRows());
		
		result = interpreter.execute("");
		ResultSet resultSet = result.getResultSet();
		int counter = 0;
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String field = resultSet.getString(2);
			Assert.assertEquals(1, id);
			Assert.assertEquals("", field);
			counter++;
		}
		Assert.assertEquals(1, counter);
	}

}