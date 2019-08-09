package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;

public class InvalidCommandValidation implements CommandValidator {
    @Override
    public CommandExecutor validate(Game game) {
        return new InvalidCommandExec(new GameChangeResponse(Status.ERROR, "invalid Command"));
    }
}
