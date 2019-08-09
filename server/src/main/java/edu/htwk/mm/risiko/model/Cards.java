package edu.htwk.mm.risiko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Cards {
	
	INFANTERY("Infaterie"),
	CAVALRY("Kavallerie"),
	ARTILLERY("Artillery"),
	JOKER("Joker")
	;
	
	private String name;
}
