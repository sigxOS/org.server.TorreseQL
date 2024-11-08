package it.naples.TorreseQL.model;

import java.util.ArrayList;
import java.util.List;

public class QueryInfo {
	public enum QueryType {
		SELECT,
		UPDATE,
		DELETE,
		INSERT,
		COMMIT,
		BEGIN,
		ROLLBACK
    }

	private QueryType type;

	private String tableName;

	private final List<String> columnNames = new ArrayList<>();
	private final List<String> values = new ArrayList<>();

	private final List<String> joinedTables = new ArrayList<>();
	private final List<String> joinOperators = new ArrayList<>();

	private final List<Condition> conditions = new ArrayList<>();

	public QueryType getType() {
		return type;
	}

	public void setType(QueryType type) {
		this.type = type;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void addColumnName(String columnName) {
		this.columnNames.add(columnName);
	}

	public List<String> getValues() {
		return values;
	}

	public List<String> getJoinedTables() {
		return joinedTables;
	}

	public List<String> getJoinOperators() {
		return joinOperators;
	}

	public List<Condition> getWhereConditions() {
		return conditions;
	}

	public void addValue(String value) {
		this.values.add(value);
	}

	public void addJoinedTable(String joinedTable) {
		this.joinedTables.add(joinedTable);
	}

	public void addCondition(Condition condition) {
		this.conditions.add(condition);
	}

	public void addConditionsOperator(String operator) {
		this.joinOperators.add(operator);
	}

}
