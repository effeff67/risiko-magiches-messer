package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.FinishTurnExec;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;

public class FinishTurnValidator implements CommandValidator {

    private final GameChangeRequest command;
    private final GameChangeResponse response;

    public FinishTurnValidator(GameChangeRequest commandRequest) {
        this.command = commandRequest;
        this.response = new GameChangeResponse(Status.ERROR);
    }

    @Override
    public CommandExecutor validate(Game game) {
        if(game.getActivePlayer() != command.getPlayer().getColor()) {
            return new InvalidCommandExec(response.setMessage("Du bist gar nicht dran!"));
        }
        return new FinishTurnExec(game, response.setStatus(Status.SUCCESS));
    }
}
