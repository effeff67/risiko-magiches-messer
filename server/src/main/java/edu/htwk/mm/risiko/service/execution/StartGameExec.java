package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;

public class StartGameExec implements CommandExecutor {

    private final Game game;
    private final GameCommandRequest command;
    private final GameChangeResponse response;

    public StartGameExec(Game game, GameCommandRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        return response;
    }
}
