package it.naples.TorreseQL.model;

public class Condition {
	private final String field;
	private String value;
	private String operator;

	public Condition(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
