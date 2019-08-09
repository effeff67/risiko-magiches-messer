package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.execution.StartGameExec;

public class StartGameValidator implements CommandValidator {

    private final GameCommandRequest request;
    private final GameChangeResponse response;

    public StartGameValidator(GameCommandRequest commandRequest) {
        this.request = commandRequest;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        if(game.getPlayers().size() > 1 && game.getPlayers().size() <= 5
                && !game.isStarted()) {
            return new StartGameExec(game, request, response);
        } else {
            response.setStatus(Status.ERROR);
            response.setMessage("Anzahl der Spieler ins falsch oder das Spiel wurde wurde schon gestartet");
            return new InvalidCommandExec(response);
        }
    }
}
