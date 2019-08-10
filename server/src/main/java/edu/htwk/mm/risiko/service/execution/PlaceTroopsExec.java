package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;

public class PlaceTroopsExec implements CommandExecutor {

    private Game game;
    private GameCommandRequest command;
    private GameChangeResponse response;

    public PlaceTroopsExec(Game game, GameCommandRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        return null;
    }
}
