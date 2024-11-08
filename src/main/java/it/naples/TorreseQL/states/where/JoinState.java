package it.naples.TorreseQL.states.where;

import java.util.Arrays;

import it.naples.TorreseQL.Keywords;
import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;
import it.naples.TorreseQL.states.AbstractState;

public class JoinState extends AbstractState {
	public JoinState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		if (token.equalsIgnoreCase(Keywords.AND)) {
			queryInfo.addConditionsOperator("AND");
			return new FieldState(queryInfo);
		}

		if (token.equalsIgnoreCase(Keywords.OR)) {
			queryInfo.addConditionsOperator("OR");
			return new FieldState(queryInfo);
		}

		throw new iDontKnow(
				Arrays.asList(
						Keywords.AND,
						Keywords.OR
				), token
		);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}