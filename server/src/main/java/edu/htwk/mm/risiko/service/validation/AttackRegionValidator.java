package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.AttackRegionExec;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;

public class AttackRegionValidator implements CommandValidator {

    GameChangeResponse response;
    GameChangeRequest command;

    public AttackRegionValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        response.setStatus(Status.ERROR);
       if(!command.getCommandDetails().get(country1).getNeighborhood().contains(command.getCommandDetails().get(country2))){
            response.setMessage("Die Länder liegen nicht benachbart.");
            return new InvalidCommandExec(response);
       }
       if (command.getCommandDetails().get(troops) <= command.getCommandDetails().get(country1).getTroopcount()) {
           response.setMessage("Das angreifende Land ist nicht hoch genug besetzt.");
           return new InvalidCommandExec(response);
       }
       response.setStatus(Status.SUCCESS);
       response.setMessage("Angriff von " + " nach " + " mit " + " Truppen durchgeführt.");
       return new AttackRegionExec(game, command, response);
    }
}
