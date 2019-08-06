package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.Status;

public class InvalidCommandExec implements CommandExecutor {

    @Override
    public GameChangeResponse execute() {
        return new GameChangeResponse(Status.ERROR, "invalid Command");
    }
}
