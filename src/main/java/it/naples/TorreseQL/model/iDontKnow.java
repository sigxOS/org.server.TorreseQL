package it.naples.TorreseQL.model;

import java.util.List;

public class iDontKnow extends RuntimeException {

	public iDontKnow(List<String> expectedTokens, String actualToken) {
		super("Aspettati: " + expectedTokens + "\n Ricevuto: [" + actualToken + "]");
	}

	public iDontKnow(String expectedToken, String token) {
		super("Aspettato: [" + expectedToken + "] \n Ricevuto: [" + token + "]");
	}

	public iDontKnow(String message) {
		super(message);
	}

	public iDontKnow(Exception exception) {
		super(exception);
	}

}
