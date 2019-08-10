package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;

public class MoveTroopsExec implements CommandExecutor {

    private Game game;
    private GameChangeRequest command;
    private GameChangeResponse response;

    public MoveTroopsExec(Game game, GameChangeRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }
    @Override
    public GameChangeResponse execute() {
        command.getCommandDetails().get(country1).setTroopcount(command.getCommandDetails().get(country1).getTroopcount) - command.getCommandDetails().get(troops);
        command.getCommandDetails().get(country2).setTroopcount(command.getCommandDetails().get(country2).getTroopcount) + command.getCommandDetails().get(troops);
        response.setMessage("Truppen erfolgreich von " + " nach " + " bewegt.");
        return response;
    }
}
