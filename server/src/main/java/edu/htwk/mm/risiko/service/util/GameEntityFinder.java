package edu.htwk.mm.risiko.service.util;

import edu.htwk.mm.risiko.model.Color;
import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.GameMap;
import edu.htwk.mm.risiko.model.Player;

public class GameEntityFinder {

    public static Country findCountryByName(GameMap map, String region) {
        return map.getContinentList().stream()
                .flatMap( continent -> continent.getCountries().stream() )
                .filter( country -> country.getRegion().getName().toLowerCase().equals(region))
                .findFirst().orElse(null);
    }

    public static Player findPlayerByColor(Game game, Color color) {
        return game.getPlayers().stream().filter(player -> player.getColor() == color).findFirst().orElse(null);
    }
}
