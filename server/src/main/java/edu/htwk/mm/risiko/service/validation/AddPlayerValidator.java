package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.service.execution.AddPlayerExec;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;

public class AddPlayerValidator implements CommandValidator {

    GameChangeResponse response;
    GameChangeRequest command;

    public AddPlayerValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        response.setStatus(Status.ERROR);
        if(game.getPlayers().stream()
                .anyMatch(player -> player.getColor() == command.getPlayer().getColor() )) {
            response.setMessage("Diese Farbe ist bereits vergeben.");
            return new InvalidCommandExec(response);
        }
        if(game.isStarted()) {
            response.setMessage("Dieses Spiel ist bereits gestartet.");
            return new InvalidCommandExec(response);
        }
        if(game.getPlayers().size() >= 5) {
            response.setMessage("Dieses Spiel hat bereits genügend Mitspieler.");
            return new InvalidCommandExec(response);
        }
        response.setStatus(Status.SUCCESS);
        return new AddPlayerExec(game, command, response);
    }


}
