package it.naples.TorreseQL.states.query;

import java.util.Arrays;

import it.naples.TorreseQL.Keywords;
import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;
import it.naples.TorreseQL.states.AbstractState;
import it.naples.TorreseQL.states.ConsumerState;
import it.naples.TorreseQL.states.TokenState;
import it.naples.TorreseQL.states.where.FieldState;

public class UpdateState extends AbstractState {
	public UpdateState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		if (token.equalsIgnoreCase(","))
			return new ConsumerState(
					queryInfo,
					queryInfo::addColumnName,
					firstPart -> new TokenState(
							firstPart,
							Keywords.EQUAL,
							secondPart -> new ConsumerState(
									secondPart,
									secondPart::addValue,
									UpdateState::new
							)
					)
			);

		if (token.equalsIgnoreCase(Keywords.WHERE))
			return new FieldState(queryInfo);


		throw new iDontKnow(
				Arrays.asList(
						",",
						Keywords.WHERE,
						"%endOfQuery%"
				), token
		);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}