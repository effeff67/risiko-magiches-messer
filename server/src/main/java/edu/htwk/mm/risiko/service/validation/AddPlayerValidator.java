package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;
import edu.htwk.mm.risiko.service.execution.AddPlayerExec;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;

public class AddPlayerValidator implements CommandValidator {

    GameChangeResponse response;
    GameCommandRequest command;

    public AddPlayerValidator(GameCommandRequest command) {
        this.command = command;
        this.response =new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        boolean used = false;
        for(int i = 0; i < game.getPlayers().size(); i++){
            if(command.getPlayer().getColor() ==  game.getPlayers().get(i).getColor()){
                used = true;
            }
        }
        if(game.getPlayers().size() < 5 && used == false )
                {
            return new AddPlayerExec(game, command, response);
        } else {
            response.setStatus(Status.ERROR);
            response.setMessage("Das Spiel ist schon voll bzw. ist die gewählte Farbe bereits vergeben.");
            return new InvalidCommandExec(response);
        }
    }
}
