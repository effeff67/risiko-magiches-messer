package edu.htwk.mm.risiko.service.validation;

import com.sun.net.httpserver.Authenticator;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.DrawCardExec;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.util.GameEntityFinder;

public class DrawCardValidator implements CommandValidator {

    GameChangeResponse response;
    GameChangeRequest command;

    public DrawCardValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse(Status.ERROR);
    }

    @Override
    public CommandExecutor validate(Game game) {
        Player player = GameEntityFinder.findPlayerByColor(game, command.getPlayer().getColor());
        if(player.getColor() != game.getLastOccupation().getColor()){
            return new InvalidCommandExec(response.setMessage("Sie Haben diese Kampfphase nicht erfolgreich erobert. Sie bekommen keine Karte."));
        }
        response.setStatus(Status.SUCCESS);
        return new DrawCardExec(game, command, response.setMessage("Sie haben erfolreich erobert, Sie bekommen eine Karte."));
    }
}
