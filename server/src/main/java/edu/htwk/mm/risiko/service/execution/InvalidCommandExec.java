package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;

public class InvalidCommandExec implements CommandExecutor {

    private GameChangeResponse response;

    public InvalidCommandExec(GameChangeResponse response) {
        this.response = response;
        response.setStatus(Status.ERROR);
    }

    @Override
    public GameChangeResponse execute() {
        return response;
    }
}
