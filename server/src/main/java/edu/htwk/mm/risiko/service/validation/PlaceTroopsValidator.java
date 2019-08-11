package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommand;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.execution.PlaceTroopsExec;

public class PlaceTroopsValidator implements CommandValidator {

    private GameChangeRequest command;
    private GameChangeResponse response;

    public PlaceTroopsValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        response.setStatus(Status.ERROR);
     if(command.getCommandDetails().get(country).getHolder() == command.getPlayer().getColor()) {
         response.setMessage("Du kontrollierst das gewählte Land nicht.");
         return new InvalidCommandExec(response);
     }
     if(command.getCommandDetails().get(troops) > command.getPlayer().getInactiveTroops()){
         response.setMessage("Du besitzt nicht genügend Truppen.");
         return new InvalidCommandExec(response);
     }
        response.setStatus(Status.SUCCESS);
     response.setMessage("Es wurden " + " Truppen auf " + " platziert.");
     return new PlaceTroopsExec(game, command, response);
    }
}
