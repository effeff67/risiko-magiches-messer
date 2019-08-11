package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;

import static edu.htwk.mm.risiko.model.Status.SUCCESS;

public class PlaceTroopsExec implements CommandExecutor {

    private final Game game;
    private final GameChangeResponse response;
    private final Player player;
    private final Country country;
    private final int troopCount;

    public PlaceTroopsExec(Game game, GameChangeResponse response, Player player, Country country, int troopCount) {
        this.game = game;
        this.response = response;
        this.player = player;
        this.country = country;
        this.troopCount = troopCount;
    }

    @Override
    public GameChangeResponse execute() {
        country.setTroopCount(troopCount);
        player.setInactiveTroops(player.getInactiveTroops() - troopCount);
        response.setStatus(SUCCESS);
        response.setMessage(String.format("Es wurden erfolgreich %s Figuren auf %s gesetzt.", troopCount, country.getRegion().getName()));
        return response;
    }
}
