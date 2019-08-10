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

public class PlaceTroopValidator implements CommandValidator {

    private GameChangeRequest command;
    private GameChangeResponse response;

    public PlaceTroopValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) { //todo country und troops als HashMap values
        response.setStatus(Status.ERROR);
        if(command.getCommandDetails().get(country).getHolder() == command.getPlayer().getColor()) {
            response.setMessage("Du kontrollierst das gewählte Land nicht.");
            return new InvalidCommandExec(response);
        }
        if(1 > command.getPlayer().getInactiveTroops()){
            response.setMessage("Du besitzt nicht genügend Truppen.");
            return new InvalidCommandExec(response);
        }
        response.setStatus(Status.SUCCESS);
        response.setMessage("Es wurde eine Truppe auf " + " platziert.");
        return new PlaceTroopsExec(game, command, response);
    }
}

