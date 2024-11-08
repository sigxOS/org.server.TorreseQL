package it.naples.TorreseQL.states;

import java.util.function.Function;

import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;

public class TokenState extends AbstractState {
	private final String expectedToken;
	private final Function<QueryInfo, AbstractState> transitionFunction;

	public TokenState(QueryInfo queryInfo, String expectedToken, Function<QueryInfo, AbstractState> transitionFunction) {
		super(queryInfo);

		this.expectedToken = expectedToken;
		this.transitionFunction = transitionFunction;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		if (token.equalsIgnoreCase(expectedToken))
			return transitionFunction.apply(queryInfo);

		throw new iDontKnow(
				expectedToken,
				token
		);
	}

}