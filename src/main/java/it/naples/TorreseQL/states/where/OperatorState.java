package it.naples.TorreseQL.states.where;

import it.naples.TorreseQL.Keywords;
import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;
import it.naples.TorreseQL.model.Condition;
import it.naples.TorreseQL.states.AbstractState;
import it.naples.TorreseQL.states.MatchState;

public class OperatorState extends AbstractState {

	private final Condition condition;

	public OperatorState(QueryInfo queryInfo, Condition condition) {
		super(queryInfo);
		
		this.condition = condition;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		if (Keywords.OPERATORS.contains(token) || token.equalsIgnoreCase(Keywords.IS)) {
			if (token.equalsIgnoreCase(Keywords.ISNT[0])) {
				condition.setOperator("IS NOT");
			
				return new MatchState(
						queryInfo,
						Keywords.ISNT,
						firstPart -> new ConditionState(
								firstPart,
								condition
						)
				);
			}
			
			if (token.equalsIgnoreCase(Keywords.IS)) 
				condition.setOperator("IS");
			else 
				condition.setOperator(token);
			
			return new ConditionState(
					queryInfo,
					condition
			);
		}
		throw new iDontKnow(Keywords.OPERATORS, token);
	}

}