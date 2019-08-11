package edu.htwk.mm.risiko.service.validation;

import com.sun.net.httpserver.Authenticator;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.DrawCardExec;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;

public class DrawCardValidator implements CommandValidator {

    GameChangeResponse response;
    GameChangeRequest command;

    public DrawCardValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        response.setStatus(Status.ERROR);
        if(!command.getPlayer().isConquered()){
            response.setMessage("Sie Haben diese Kampfphase nicht erfolgreich erobert. Sie bekommen keine Karte.");
            return new InvalidCommandExec(response);
        }
        response.setStatus(Status.SUCCESS);
        response.setMessage("Sie haben erfolreich erobert, Sie bekommen eine Karte.");
        return new DrawCardExec(game, command, response);
    }
}
