package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Occupation;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.execution.MoveTroopsExec;
import edu.htwk.mm.risiko.service.util.GameEntityFinder;

public class MoveTroopAfterOccupationValidation implements CommandValidator {

    private final GameChangeRequest request;
    private final GameChangeResponse response;

    public MoveTroopAfterOccupationValidation(GameChangeRequest request) {
        this.request = request;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        Occupation occupation = game.getLastOccupation();
        if(occupation.getColor() != request.getPlayer().getColor()) {
            return new InvalidCommandExec(response.setMessage("Dieses Land hast du nicht erobert"));
        }
        Integer countTroops = (Integer)request.getCommandDetails().get("countTroops");
        if(null == countTroops) {
            return new InvalidCommandExec(response.setMessage("Anzahl der Truppen invalid!"));
        }

        Country source = GameEntityFinder.findCountryByName(game.getGameMap(), occupation.getSource().getName());
        if(source.getTroopCount() <= countTroops + 1) {
            return new InvalidCommandExec(response.setMessage("Nicht genügend Truppen!"));
        }
        Country target = GameEntityFinder.findCountryByName(game.getGameMap(), occupation.getTarget().getName());
        response.setStatus(Status.SUCCESS);
        return new MoveTroopsExec(game, source, target, countTroops, response);
    }
}
