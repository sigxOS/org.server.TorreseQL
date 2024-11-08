package it.naples.TorreseQL.states;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;

public class ValueState extends AbstractState {
	private final Function<QueryInfo, AbstractState> transitionFunction;
	private final List<String> collector;
	private final String nextToken;
	private final String expectedToken;

	private boolean lastWasComma = false;
	private boolean canBeFinalState = false;
	private boolean optionalValues = false;

	public ValueState(QueryInfo queryInfo, List<String> collector, String nextToken, String expectedToken, Function<QueryInfo, AbstractState> transitionFunction) {
		super(queryInfo);

		this.collector = collector;
		this.nextToken = nextToken;
		this.transitionFunction = transitionFunction;
		this.expectedToken = expectedToken;
	}

	public ValueState(QueryInfo queryInfo, List<String> collector, String nextToken, String expectedToken, boolean lastWasComma, boolean canBeFinalState, Function<QueryInfo, AbstractState> transitionFunction) {
		this(queryInfo, collector, nextToken, expectedToken, transitionFunction);

		this.lastWasComma = lastWasComma;
		this.canBeFinalState = canBeFinalState;
		this.optionalValues = true;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		if (token.equals(",")) {
			if (lastWasComma)
				throw new iDontKnow(expectedToken, token);
			else {
				lastWasComma = true;
				return this;
			}
		}

		if (lastWasComma) {
			if (optionalValues && token.equalsIgnoreCase(nextToken))
				return transitionFunction.apply(queryInfo);
			else
				optionalValues = false;

			collector.add(token);
			lastWasComma = false;

			return this;
		}

		if (token.equalsIgnoreCase(nextToken))
			return transitionFunction.apply(queryInfo);

		throw new iDontKnow(Arrays.asList(",", nextToken), token);
	}

	@Override
	public boolean isFinalState() {
		return canBeFinalState;
	}
}