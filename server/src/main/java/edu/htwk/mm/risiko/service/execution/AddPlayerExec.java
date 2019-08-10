package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;

public class AddPlayerExec implements CommandExecutor {

    private Game game;
    private GameChangeRequest command;
    private GameChangeResponse response;

    public AddPlayerExec(Game game, GameChangeRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {

        return response;
    }
}
