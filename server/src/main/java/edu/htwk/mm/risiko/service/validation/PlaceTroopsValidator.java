package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommand;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.execution.PlaceTroopsExec;

public class PlaceTroopsValidator implements CommandValidator {

    private GameCommandRequest command;
    private GameChangeResponse response;

    public PlaceTroopsValidator(GameCommandRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game /* , Country country, int troops */) {
       /* if(country.getHolder() == command.getPlayer().getColor() && troops <= command.getPlayer().getInactiveTroops()) {
            return new PlaceTroopsExec(game, command, response);
        } else {
            response.setStatus(Status.ERROR);
            response.setMessage("Die gewählte Region befindet sich nicht unter deiner Kontrolle oder du besitzt nicht genügend Truppen.");*/
            return new InvalidCommandExec(response);
        }
    }
/*}*/
