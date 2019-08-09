package edu.htwk.mm.risiko.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;
    private Color color;
	private Mission mission;
	private int inactiveTroops;
	private CardsStack cards;

	public Player (String name, Color color) {
		this.name = name;
		this.color = color;
	}
}
