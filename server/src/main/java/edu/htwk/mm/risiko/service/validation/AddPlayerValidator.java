package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;
import edu.htwk.mm.risiko.service.execution.AddPlayerExec;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;

public class AddPlayerValidator implements CommandValidator {

    GameChangeResponse response;
    GameCommandRequest command;

    public AddPlayerValidator(GameCommandRequest command) {
        this.command = command;
        this.response =new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {

        return new AddPlayerExec(game, command, response);
    }


}
