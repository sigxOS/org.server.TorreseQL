package it.naples.TorreseQL;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.naples.TorreseQL.model.iDontKnow;

public class TestWrongSyntax {
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
	public void testWrongInitialKeyword() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testSelectDoubleComma() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testSelectWrongFrom() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testQueryEndExpected() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testWrongLongOperator() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testIncompleteQuery() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testWhereInvalidOperator() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testWrongSelectWhereJoinOperator() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testWrongSelectWhereClause() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testWrongUpdateSetClause() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
	
	@Test(expected = iDontKnow.class)
	public void testWrongUpdateWhereClause() {
		TorreseInterpreter interpreter = new TorreseInterpreter(connection);
		interpreter.execute("");
	}
}