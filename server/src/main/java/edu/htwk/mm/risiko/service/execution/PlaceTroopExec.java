package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;

import static edu.htwk.mm.risiko.model.Status.SUCCESS;

public class PlaceTroopExec implements CommandExecutor {

    private Game game;
    private GameChangeRequest command;
    private GameChangeResponse response;

    public PlaceTroopExec(Game game, GameChangeRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        command.getCommandDetails().get(country).setTroopcount(command.getCommandDetails().get(country).getTroopcount() + 1);
        command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() - 1);
        response.setMessage("Es wurde erflogreich eine Figur auf " + " gesetzt.");
        return response;
    }
}