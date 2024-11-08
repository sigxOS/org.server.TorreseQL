package it.naples.TorreseQL.states.where;

import it.naples.TorreseQL.Keywords;
import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;
import it.naples.TorreseQL.model.Condition;
import it.naples.TorreseQL.states.AbstractState;

public class ConditionState extends AbstractState {
	private final Condition condition;

	public ConditionState(QueryInfo queryInfo, Condition condition) {
		super(queryInfo);
		
		this.condition = condition;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		if (token.equalsIgnoreCase(Keywords.NULL))
			condition.setValue("NULL");
		else 
			condition.setValue(token);
		
		queryInfo.addCondition(condition);
		
		return new JoinState(queryInfo);
	}
	
}