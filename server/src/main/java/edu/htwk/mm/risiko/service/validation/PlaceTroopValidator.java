package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.execution.PlaceTroopExec;
import edu.htwk.mm.risiko.service.execution.PlaceTroopsExec;
import edu.htwk.mm.risiko.service.util.GameEntityFinder;

public class PlaceTroopValidator implements CommandValidator {

    private GameChangeRequest command;
    private GameChangeResponse response;

    public PlaceTroopValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse(Status.ERROR);
    }

    @Override
    public CommandExecutor validate(Game game) {
        Object countryName = command.getCommandDetails().get("country");
        if(countryName == null) {
            return new InvalidCommandExec(response.setMessage(String.format("kein land angegeben", countryName)));
        }
        Country country = GameEntityFinder.findCountryByName(game.getGameMap(), countryName.toString());
        if(country == null) {
            return new InvalidCommandExec(response.setMessage(String.format("das land %s exitiert auf der karte nicht", countryName)));
        }
        Player player = GameEntityFinder.findPlayerByColor(game, command.getPlayer().getColor());
        if(player.getColor() != game.getActivePlayer()) {
            return new InvalidCommandExec(response.setMessage("Du bist nicht an der Reihe."));
        }
        if(country.getHolder() != player.getColor()) {
            return new InvalidCommandExec(response.setMessage("Du kontrollierst das gewählte Land nicht."));
        }
        if(1 > player.getInactiveTroops()){
            return new InvalidCommandExec(response.setMessage("Du besitzt nicht genügend Truppen."));
        }

        return new PlaceTroopExec(game, response.setStatus(Status.SUCCESS), player, country);

    }
}

