package edu.htwk.mm.risiko.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

	private String name;
	private boolean conquerWorld;
	private boolean playing;
	private List<String> colorsInGame;
	private List<Player> allPlayers;
	

	public void addPlayer(Player player) {
		this.allPlayers.add(player);
		this.colorsInGame.add(player.getColor());
		
	}
	public void removePlayer(String color) {
		for( int i=0; i<allPlayers.size();i++) {
			if (allPlayers.get(i).getColor()==color) {
				allPlayers.remove(i);
			}
			if (colorsInGame.get(i)==color) {
				colorsInGame.remove(i);
			}
		}
	}
	
}
