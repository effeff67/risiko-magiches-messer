package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;
import edu.htwk.mm.risiko.service.execution.AddPlayerExec;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;

public class AddPlayerValidator implements CommandValidator {

    GameChangeResponse response;
    GameCommandRequest command;

    public AddPlayerValidator(GameCommandRequest command) {
        this.command = command;
        this.response =new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        if(game.getPlayers().size() < 5 ) { // todo && chosen color is not already used
            return new AddPlayerExec(game, command, response);
        } else {
            response.setStatus(Status.ERROR);
            response.setMessage("Das Spiel ist schon voll bzw. ist die gewählte Farbe bereits vergeben.");
            return new InvalidCommandExec(response);
        }
    }
}
