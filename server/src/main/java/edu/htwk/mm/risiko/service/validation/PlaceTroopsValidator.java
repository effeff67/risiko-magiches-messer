package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.execution.PlaceTroopsExec;
import edu.htwk.mm.risiko.service.util.GameEntityFinder;

public class PlaceTroopsValidator implements CommandValidator {

    private GameChangeRequest command;
    private GameChangeResponse response;

    public PlaceTroopsValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse(Status.ERROR);
    }

    @Override
    public CommandExecutor validate(Game game) {
        Object countryName = command.getCommandDetails().get("country");
        Integer troopCount = (Integer) command.getCommandDetails().get("troopCount");
        Country country = GameEntityFinder.findCountryByName(game.getGameMap(), countryName.toString());
        if (country.getHolder() == command.getPlayer().getColor()) {
            return new InvalidCommandExec(response.setMessage("Du kontrollierst das gewählte Land nicht."));
        }
        if (troopCount > command.getPlayer().getInactiveTroops()) {
            return new InvalidCommandExec(response.setMessage("Du besitzt nicht genügend Truppen."));
        }
        Player player = command.getPlayer();
        return new PlaceTroopsExec(game, response.setStatus(Status.SUCCESS), player, country, troopCount);
    }
}
