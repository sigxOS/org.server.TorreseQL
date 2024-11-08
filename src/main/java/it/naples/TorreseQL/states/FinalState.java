package it.naples.TorreseQL.states;

import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;

public class FinalState extends AbstractState {

	public FinalState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		throw new iDontKnow(
				"%endOfQuery%",
				token
		);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}