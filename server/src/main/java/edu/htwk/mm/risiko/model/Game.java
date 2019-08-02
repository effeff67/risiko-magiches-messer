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
	
	
	
}
