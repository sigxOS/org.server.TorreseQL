package it.naples.TorreseQL.states;

import it.naples.TorreseQL.Keywords;
import it.naples.TorreseQL.model.QueryInfo;
import it.naples.TorreseQL.model.QueryInfo.QueryType;
import it.naples.TorreseQL.model.iDontKnow;
import it.naples.TorreseQL.states.query.OptionalState;
import it.naples.TorreseQL.states.query.SelectState;
import it.naples.TorreseQL.states.query.UpdateState;

import java.util.Arrays;

public class InitialState extends AbstractState {
	private final int index = 0;

	public InitialState() {
		super(new QueryInfo());
	}

	@Override
	public AbstractState transitionToNextState(String token) throws iDontKnow {
		if (token.equalsIgnoreCase(Keywords.SELECT)) {
			queryInfo.setType(QueryType.SELECT);
			return new SelectState(queryInfo);
		}

		if (token.equalsIgnoreCase(Keywords.UPDATE)) {
			queryInfo.setType(QueryType.UPDATE);
			return new ConsumerState(
					queryInfo,
					queryInfo::setTableName,
					firstPart -> new TokenState(
							firstPart,
							Keywords.SET,
							secondPart -> new ConsumerState(
									secondPart,
									secondPart::addColumnName,
									thirdPart -> new TokenState(
											thirdPart, Keywords.EQUAL,
											fourthPart -> new ConsumerState(
													fourthPart,
													fourthPart::addValue,
													UpdateState::new
											)
									)
							)
					)
			);
		}

		if (token.equalsIgnoreCase(Keywords.DELETE[0])) {
			queryInfo.setType(QueryType.DELETE);
			return new MatchState(
					queryInfo,
					Keywords.DELETE,
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
		}

		if (token.equalsIgnoreCase(Keywords.INSERT[0])) {
			queryInfo.setType(QueryType.INSERT);
			return new MatchState(
					queryInfo,
					Keywords.INSERT,
					firstPart -> new ConsumerState(
							firstPart,
							firstPart::setTableName,
							secondPart -> new ValueState(
									secondPart,
									secondPart.getColumnNames(),
									Keywords.VALUES,
									"%COLUMN_NAME%",
									true,
									false,
									thirdPart -> new ValueState(
											thirdPart,
											thirdPart.getValues(),
											null,
											"%VALUE%",
											true,
											true,
											FinalState::new
									)
							)
					)
			);
		}

		if (token.equalsIgnoreCase(Keywords.COMMIT[0])) {
			queryInfo.setType(QueryType.COMMIT);
			return new MatchState(
					queryInfo,
					Keywords.COMMIT,
					FinalState::new
			);
		}

		if (token.equalsIgnoreCase(Keywords.ROLLBACK)) {
			queryInfo.setType(QueryType.ROLLBACK);
			return new FinalState(queryInfo);
		}

		if (token.equalsIgnoreCase(Keywords.BEGIN[0])) {
			queryInfo.setType(QueryType.BEGIN);
			return new MatchState(
					queryInfo,
					Keywords.BEGIN,
					FinalState::new
			);
		}

		throw new iDontKnow(
				Arrays.asList(
						Keywords.SELECT,
						Keywords.UPDATE,
						Keywords.INSERT[0],
						Keywords.DELETE[0],
						Keywords.BEGIN[0],
						Keywords.COMMIT[0],
						Keywords.ROLLBACK),
				token
		);
	}

}