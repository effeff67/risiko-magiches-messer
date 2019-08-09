package edu.htwk.mm.risiko.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

	private String name;
    private boolean conquerWorld;
	private boolean started;
	private List<Player> players;
	private Color activePlayer;

    public Game(String gameName, Player player, boolean conquerTheWorld) {
        this.name = gameName;
        this.players = new ArrayList<>();
        players.add(player);
        this.conquerWorld = conquerTheWorld;
        this.started = false;
    }
}
