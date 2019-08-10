package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;

import static edu.htwk.mm.risiko.model.Status.SUCCESS;

public class PlaceTroopsExec implements CommandExecutor {

    private Game game;
    private GameChangeRequest command;
    private GameChangeResponse response;

    public PlaceTroopsExec(Game game, GameChangeRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        command.getCommandDetails().get(country).setTroopcount(command.getCommandDetails().get(country).getTroopcount() + command.getCommandDetails().get(troops));
        command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() - command.getCommandDetails().get(troops));
        response.setStatus(SUCCESS);
        response.setMessage("Es wurden erflogreich " + " Figuren auf " + " gesetzt.");
        return response;
    }
}
