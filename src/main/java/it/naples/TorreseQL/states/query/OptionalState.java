package it.naples.TorreseQL.states.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.naples.TorreseQL.Keywords;
import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;
import it.naples.TorreseQL.model.QueryInfo.QueryType;
import it.naples.TorreseQL.states.AbstractState;
import it.naples.TorreseQL.states.ConsumerState;
import it.naples.TorreseQL.states.MatchState;
import it.naples.TorreseQL.states.where.FieldState;

public class OptionalState extends AbstractState {
	public OptionalState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		List<String> expectedKeywords = new ArrayList<>(
				Collections.singletonList(
						Keywords.WHERE
				)
		);

		if (token.equalsIgnoreCase(Keywords.WHERE))
			return new FieldState(queryInfo);

		if (queryInfo.getType().equals(QueryType.SELECT)) {
			expectedKeywords.add(Keywords.JOIN[0]);
			if (token.equalsIgnoreCase(Keywords.JOIN[0]))
				return new MatchState(
						queryInfo,
						Keywords.JOIN,
						firstPart -> new ConsumerState(
								firstPart,
								firstPart::addJoinedTable,
								OptionalState::new
						)
				);
		}

		throw new iDontKnow(
				expectedKeywords,
				token
		);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}