package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.execution.MoveTroopsExec;

public class MoveTroopValidator implements CommandValidator {

    GameChangeResponse response;
    GameChangeRequest command;

    public MoveTroopValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        response.setStatus(Status.ERROR);
        if(command.getCommandDetails().get(country1).getHolder() != command.getPlayer().getColor()){
            response.setMessage("Das gewählte Ausgangsland ist nicht unter deiner Kontrolle.");
            return new InvalidCommandExec(response);
        }
        if(command.getCommandDetails().get(country2).getHolder() != command.getPlayer().getColor()){
            response.setMessage("Das gewählte Zielland ist nicht unter deiner Kontrolle.");
            return new InvalidCommandExec(response);
        }
        if(command.getCommandDetails().get(country1).getTroopcount() < command.getCommandDetails().get(troops)){
            response.setMessage("Das Asuagngsland ist nicht hoch genug besetzt.");
            return new InvalidCommandExec(response);
        }
        response.setStatus(Status.SUCCESS);
        response.setMessage("Erfolgreich " + " Truppen von " + " nach " + " bewegt.");
        return new MoveTroopsExec(game, command, response);
    }
}
