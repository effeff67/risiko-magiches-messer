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

public class PlaceTroopValidator implements CommandValidator {

    private GameChangeRequest command;
    private GameChangeResponse response;

    public PlaceTroopValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        response.setStatus(Status.ERROR);
        Object countryName = command.getCommandDetails().get("country");
        if(countryName != null) {
            Country country = GameEntityFinder.findCountryByName(game.getGameMap(),countryName.toString());
            if(null != country) {
                if(country.getHolder() != command.getPlayer().getColor()) {
                    response.setMessage("Du kontrollierst das gewählte Land nicht.");
                    return new InvalidCommandExec(response);
                }
                if(1 > command.getPlayer().getInactiveTroops()){
                    response.setMessage("Du besitzt nicht genügend Truppen.");
                    return new InvalidCommandExec(response);
                }
                response.setStatus(Status.SUCCESS);
                Player player = GameEntityFinder.findPlayerByColor(game, command.getPlayer().getColor());
                return new PlaceTroopsExec(game, response, player, country, 1);
            }
        }
        response.setMessage("das Land existiert nicht auf der Karte ");
        return new InvalidCommandExec(response);
    }
}

