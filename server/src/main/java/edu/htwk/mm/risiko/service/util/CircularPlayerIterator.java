package edu.htwk.mm.risiko.service.util;

import edu.htwk.mm.risiko.model.Color;
import edu.htwk.mm.risiko.model.Player;

import java.util.List;

public class CircularPlayerIterator {

    private List<Player> players;
    private int iterations;

    public CircularPlayerIterator(List<Player> players, Color color) {
        this.players = players;
        iterations = 0;
        while (next().getColor() != color) {
            ;
        }
    }

    public CircularPlayerIterator(List<Player> players) {
        this.players = players;
        iterations = 0;
    }

    public Player next() {
        int idx = iterations++ % players.size();
        return players.get(idx);
    }
}
