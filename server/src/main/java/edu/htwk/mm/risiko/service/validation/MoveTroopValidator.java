package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.execution.MoveTroopsExec;
import edu.htwk.mm.risiko.service.util.GameEntityFinder;

public class MoveTroopValidator implements CommandValidator {

    private final GameChangeResponse response;
    private final GameChangeRequest command;

    public MoveTroopValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse(Status.ERROR);
    }

    @Override
    public CommandExecutor validate(Game game) {
        Object targetCountryName = command.getCommandDetails().get("targetCountry");
        Object sourceCountryName = command.getCommandDetails().get("sourceCountry");
        Integer troopCount = Integer.parseInt((String)command.getCommandDetails().get("troopCount"));
        if(null == targetCountryName) {
            response.setMessage("Das  Zielland muss bekannt sein!");
            return new InvalidCommandExec(response);
        }
        if(null == sourceCountryName) {
            response.setMessage("Das Ursprungsland muss bekannt sein!");
            return new InvalidCommandExec(response);
        }
        Country target = GameEntityFinder.findCountryByName(game.getGameMap(), targetCountryName.toString());
        if(null == target) {
            return new InvalidCommandExec(response.setMessage("Das Zielland muss auf der Karte sein!"));
        }
        Country source = GameEntityFinder.findCountryByName(game.getGameMap(), sourceCountryName.toString());
        if(null == source) {
            response.setMessage("Das Ursprungsland muss auf der Karte sein!");
            return new InvalidCommandExec(response);
        }
        if(!source.getNeighborhood().contains(target.getRegion())) {
            response.setMessage("Die Länder liegen nicht benachbart. Du kannst keine Gruppen hierhin ziehen");
            return new InvalidCommandExec(response);
        }
        if(source.getHolder() != command.getPlayer().getColor()) {
            response.setMessage("Das Ursprungsland musst du erstmal besetzen!");
            return new InvalidCommandExec(response);
        }
        if(target.getHolder() != command.getPlayer().getColor()) {
            response.setMessage("Das Zielland musst du erst noch besetzen!");
            return new InvalidCommandExec(response);
        }
        if(source.getTroopCount() - troopCount < 1) {
            response.setMessage("Das Ursprungsland ist nicht hoch genug besetzt.");
            return new InvalidCommandExec(response);
        }

        return new MoveTroopsExec(game, source, target, troopCount,response.setStatus(Status.SUCCESS));
    }
}
