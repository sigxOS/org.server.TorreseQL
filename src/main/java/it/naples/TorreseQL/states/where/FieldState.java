package it.naples.TorreseQL.states.where;

import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;
import it.naples.TorreseQL.model.Condition;
import it.naples.TorreseQL.states.AbstractState;

public class FieldState extends AbstractState {

	public FieldState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		return new OperatorState(
				queryInfo,
				new Condition(token)
		);
	}

}