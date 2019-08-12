package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.util.CircularPlayerIterator;

public class FinishTurnExec implements CommandExecutor {

    private final Game game;
    private final GameChangeResponse response;

    public FinishTurnExec(Game game, GameChangeResponse response) {
        this.game = game;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        CircularPlayerIterator iterator = new CircularPlayerIterator(game.getPlayers(), game.getActivePlayer());
        game.setActivePlayer(iterator.next().getColor());
        return response.setMessage("der nächste bitte!");
    }
}
