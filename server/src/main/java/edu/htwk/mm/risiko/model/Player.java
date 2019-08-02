package edu.htwk.mm.risiko.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
	private String name;
	private String color;
	private Mission mission;
	private int openTroops;
	private CardsStack cards;
	private List<PlayersCountry> countrys;
	private List<Continent> continents;
	
	public Player (String name, String color) {
		this.name=name;
		this.color=color;
	}
}
