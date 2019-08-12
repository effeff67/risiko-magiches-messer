package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.Turns;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.util.CircularPlayerIterator;

import java.util.Arrays;

import static edu.htwk.mm.risiko.model.Status.SUCCESS;

public class PlaceTroopExec implements CommandExecutor {

    private final Game game;
    private final GameChangeResponse response;
    private final Player player;
    private final Country country;

    public PlaceTroopExec(Game game, GameChangeResponse response, Player player, Country country) {
        this.game = game;
        this.response = response;
        this.player = player;
        this.country = country;
    }

    @Override
    public GameChangeResponse execute() {
        country.setTroopCount(country.getTroopCount() + 1);
        player.setInactiveTroops(player.getInactiveTroops() - 1);
        CircularPlayerIterator iter = new CircularPlayerIterator(game.getPlayers(), player.getColor());
        if(game.getPlayers().stream().allMatch(p -> p.getInactiveTroops() == 0)) {
            game.setReady(true);
            game.setActivePlayer(iter.next().getColor());
            game.setActiveTurns(Arrays.asList(Turns.values()));
        } else {
            do {
                Player next = iter.next();
                if (next.getInactiveTroops() > 0) {
                    game.setActivePlayer(next.getColor());
                    break;
                }
            } while (true);
        }
        return response.setMessage(String.format("Es wurden erfolgreich %s Figuren auf %s gesetzt.", 1, country.getRegion().getName()));
    }
}
