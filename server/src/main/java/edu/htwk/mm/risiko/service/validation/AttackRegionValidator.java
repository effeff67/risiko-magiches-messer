package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.AttackRegionExec;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.util.GameEntityFinder;

public class AttackRegionValidator implements CommandValidator {

    private final GameChangeResponse response;
    private final GameChangeRequest command;

    public AttackRegionValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse(Status.ERROR);
    }

    @Override
    public CommandExecutor validate(Game game) {
        Object targetCountryName = command.getCommandDetails().get("targetCountry");
        Object sourceCountryName = command.getCommandDetails().get("sourceCountry");
        Integer troopCount = (Integer) command.getCommandDetails().get("troopCount");
        if(null == targetCountryName) {
            response.setMessage("Das attackierte Land muss bekannt sein!");
            return new InvalidCommandExec(response);
        }
        if(null == sourceCountryName) {
            response.setMessage("Das attackierende Land muss bekannt sein!");
            return new InvalidCommandExec(response);
        }
        Country target = GameEntityFinder.findCountryByName(game.getGameMap(), targetCountryName.toString());
        if(null == target) {
            response.setMessage("Das attackierte Land muss auf der Karte sein!");
            return new InvalidCommandExec(response);
        }
        Country source = GameEntityFinder.findCountryByName(game.getGameMap(), sourceCountryName.toString());
        if(null == source) {
            response.setMessage("Das attackierende Land muss auf der Karte sein!");
            return new InvalidCommandExec(response);
        }
        if(!source.getNeighborhood().contains(target.getRegion())) {
            response.setMessage("Die Länder liegen nicht benachbart.");
            return new InvalidCommandExec(response);
        }
        if(source.getHolder() != command.getPlayer().getColor()) {
            response.setMessage("Das attackierende Land musst du erstmal besetzen!");
            return new InvalidCommandExec(response);
        }
        if(target.getHolder() == command.getPlayer().getColor()) {
            response.setMessage("Das attackierte Land hast du schon besetzt!");
            return new InvalidCommandExec(response);
        }
        if(source.getTroopCount() - troopCount < 1) {
            response.setMessage("Das angreifende Land ist nicht hoch genug besetzt.");
            return new InvalidCommandExec(response);
        }
        response.setStatus(Status.SUCCESS);
        return new AttackRegionExec(game, command.getPlayer(), source, target, troopCount, response);
    }
}
