package edu.htwk.mm.risiko.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Player {
    private String name;
    private Color color;
	private Missions mission;
	private int inactiveTroops;
	private CardsStack cards;
	private boolean conquered = false;

	public Player (String name, Color color) {
		this.name = name;
		this.color = color;
	}
}
