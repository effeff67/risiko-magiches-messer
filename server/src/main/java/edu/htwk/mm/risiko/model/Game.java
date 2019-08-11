package edu.htwk.mm.risiko.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

	private String name;
    private boolean conquerWorld;
    private List<Color> availableColors;
	private boolean started;
	private List<Player> players;
	private Color activePlayer;
	private GameMap gameMap;
	private List<Mission> missions;
	private List<Cards> cards;
	private int tradeCounts;
	private Occupation lastOccupation;

    public Game(String gameName, Player player, GameMap gameMap, boolean conquerTheWorld) {
        this.name = gameName;
        this.availableColors = new ArrayList<>(Arrays.asList(Color.values()));
        this.players = new ArrayList<>();
        players.add(player);
        this.availableColors.remove(player.getColor());
        this.gameMap = gameMap;
        this.conquerWorld = conquerTheWorld;
        if(!conquerTheWorld) {
            this.missions = Arrays.asList(Mission.values());
        } else {
            this.missions = Collections.emptyList();
        }
        cards = Arrays.asList(Cards.values());
        tradeCounts = 0;
        this.started = false;
    }
}
