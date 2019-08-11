package edu.htwk.mm.risiko.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Player {
    private String name;
    private Color color;
	private Mission mission;
	private int inactiveTroops;
	private List<Cards> cards;
	private boolean conquered = false;

	public Player (String name, Color color) {
		this.name = name;
		this.color = color;
	}
}
