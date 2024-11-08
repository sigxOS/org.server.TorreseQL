package it.naples.TorreseQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.StringTokenizer;

import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryResult;
import it.naples.TorreseQL.model.QueryInfo;
import it.naples.TorreseQL.model.Condition;
import it.naples.TorreseQL.states.AbstractState;
import it.naples.TorreseQL.states.InitialState;

public class TorreseInterpreter {

	private final Connection connection;

	public TorreseInterpreter(Connection connection) {
		this.connection = connection;
	}

	public static String toSqlQuery(String query) {
		QueryInfo info = parseQuery(query);

		return buildQuery(info);
	}

	private static QueryInfo parseQuery(String query) {
		String delimiter = ", ";

		AbstractState currentState = new InitialState();
		StringTokenizer tokenizer = new StringTokenizer(
				query,
				delimiter,
				true
		);

		while (tokenizer.hasMoreTokens()) {
			String nextToken = tokenizer.nextToken().trim();

			if (!nextToken.isEmpty())
				currentState = currentState.transitionToNextState(nextToken);
		}

		if (!currentState.isFinalState())
			throw new iDontKnow("Query: Fine Inaspettata");

		return currentState.getQueryInfo();
	}

	private static String buildQuery(QueryInfo queryInfo) {
		switch (queryInfo.getType()) {
			case SELECT:
				return selectQuery(queryInfo);
			case INSERT:
				return insertQuery(queryInfo);
			case UPDATE:
				return updateQuery(queryInfo);
			case DELETE:
				return deleteQuery(queryInfo);
			case BEGIN:
				return "START TRANSACTION";
			case COMMIT:
				return "COMMIT";
			case ROLLBACK:
				return "ROLLBACK";
		}
		throw new iDontKnow("Query: Sconosciuta");
	}

	private static String deleteQuery(QueryInfo queryInfo) {
		return "DELETE FROM " + queryInfo.getTableName() + buildClause(queryInfo);
	}

	private static String buildClause(QueryInfo queryInfo) {
		List<Condition> conditions = queryInfo.getWhereConditions();
		List<String> joinOperators = queryInfo.getJoinOperators();

		if (conditions.isEmpty())
			return "";

		StringBuilder whereClause = new StringBuilder(" WHERE ");

		for (int i = 0; i < conditions.size(); i++) {
			Condition condition = conditions.get(i);

			whereClause.append(
					condition.getField()
			).append(" ").append(
					condition.getOperator()
			).append(" ").append(
					condition.getValue()
			);

			if (joinOperators.size() >= i + 1)
				whereClause.append(" ").append(
						joinOperators.get(i)
				).append(" ");
		}

		return whereClause.toString();
	}

	private static String updateQuery(QueryInfo queryInfo) {
		StringBuilder query = new StringBuilder("UPDATE ").append(
				queryInfo.getTableName()
		).append(" SET ");

		for (int i = 0; i < queryInfo.getColumnNames().size(); i++)
			query.append(
					queryInfo.getColumnNames().get(i)
			).append(" = ").append(
					queryInfo.getValues().get(i)
			).append(", ");

		query.delete(
				query.length() - 2,
				query.length()
		);

		return query + buildClause(queryInfo);
	}

	private static String insertQuery(QueryInfo queryInfo) {
		StringBuilder query = new StringBuilder("INSERT INTO ").append(
				queryInfo.getTableName()
		);

		if (!queryInfo.getColumnNames().isEmpty())
			query.append(" ( ").append(
					String.join(
							", ",
							queryInfo.getColumnNames()
					)
			).append(" )");

		query.append(" VALUES ( ").append(
				String.join(
						", ",
						queryInfo.getValues()
				)
		).append(" )");

		return query.toString();
	}

	private static String selectQuery(QueryInfo queryInfo) {
		String query = "SELECT ";

		query += String.join(
				", ",
				queryInfo.getColumnNames()
		);

		query += " FROM " + queryInfo.getTableName();

		List<String> joinedTables = queryInfo.getJoinedTables();
		if (!joinedTables.isEmpty())
			query += " INNER JOIN " + String.join(
					" INNER JOIN ",
					joinedTables
			);

		query += buildClause(queryInfo);

		return query;
	}

	public QueryResult execute(String query) {
		QueryInfo queryInfo = parseQuery(query);
		String built = buildQuery(queryInfo);
		QueryResult result = new QueryResult();

		try {
			Statement statement = connection.createStatement();

			switch (queryInfo.getType()) {
				case SELECT: {
					ResultSet resultSet = statement.executeQuery(built);
					result.setResultSet(resultSet);
				}

				case BEGIN: {
					connection.setAutoCommit(false);
				}

				case COMMIT: {
					connection.commit();
					connection.setAutoCommit(true);
				}

				case DELETE:
				case UPDATE:
				case INSERT: {
					int updatedRows = statement.executeUpdate(built);
					result.setAffectedRows(updatedRows);
				}

				case ROLLBACK: {
					connection.rollback();
					connection.setAutoCommit(true);
				}
			}
		} catch (SQLException exception) {
			throw new iDontKnow(exception);
		}

		return result;
	}

}