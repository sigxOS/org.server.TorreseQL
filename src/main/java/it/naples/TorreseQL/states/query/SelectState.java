package it.naples.TorreseQL.states.query;

import it.naples.TorreseQL.Keywords;
import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.model.QueryInfo;
import it.naples.TorreseQL.states.AbstractState;
import it.naples.TorreseQL.states.ConsumerState;
import it.naples.TorreseQL.states.ValueState;
import it.naples.TorreseQL.states.MatchState;

public class SelectState extends AbstractState {
	private final int index = 0;

	public SelectState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		if (token.equalsIgnoreCase(Keywords.ALL[0])) {
			queryInfo.addColumnName("*");

			return new MatchState(
					queryInfo,
					Keywords.ALL,
					firstPart -> new MatchState(
							firstPart,
							Keywords.FROM,
							secondPart -> new ConsumerState(
									secondPart,
									secondPart::setTableName,
									OptionalState::new
							), index
					)
			);
		} else {
			queryInfo.addColumnName(token);

			return new ValueState(
					queryInfo,
					queryInfo.getColumnNames(),
					Keywords.FROM[0],
					"%columnNames%",
					firstPart -> new MatchState(
							queryInfo,
							Keywords.FROM,
					secondPart -> new ConsumerState(
							secondPart,
							secondPart::setTableName,
							OptionalState::new
					))
			);
		}
	}
}