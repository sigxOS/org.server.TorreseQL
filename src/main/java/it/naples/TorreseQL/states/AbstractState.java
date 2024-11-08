package it.naples.TorreseQL.states;

import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;

public abstract class AbstractState {

	protected QueryInfo queryInfo;

	public AbstractState(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}

	public boolean isFinalState() {
		return false;
	}

	public QueryInfo getQueryInfo() {
		return queryInfo;
	}

	public abstract AbstractState transitionToNextState(String token) throws iDontKnow;

}