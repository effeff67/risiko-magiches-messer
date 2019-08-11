package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Continent;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.RecruitTroopsExec;
import edu.htwk.mm.risiko.service.util.GameEntityFinder;

import java.util.ArrayList;
import java.util.List;

public class RecruitTroopsValidator implements CommandValidator  {

    private final GameChangeResponse response;
    private final GameChangeRequest command;

    public RecruitTroopsValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        int countryCount = calculateCountryCount(game);
        List<Continent> continents = collectContientsOwnedByPlayer(game,command.getPlayer());
        Player player = GameEntityFinder.findPlayerByColor(game, command.getPlayer().getColor());
        return new RecruitTroopsExec(game, player, continents, countryCount, response);
    }

    private List<Continent> collectContientsOwnedByPlayer(Game game, Player player) {
        List<Continent> list = new ArrayList<>();
        game.getGameMap().getContinentList().forEach(continent -> {
            if(continent.getCountries().stream().allMatch( country -> country.getHolder() == player.getColor())){
                list.add(continent);
            }
        });
        return list;
    }

    private int calculateCountryCount(Game game) {
        int countries = 0;
        for(int i = 0; i < game.getGameMap().getContinentList().size(); i++){
            for(int j = 0; j < game.getGameMap().getContinentList().get(i).getCountries().size(); j++){
                if(game.getGameMap().getContinentList().get(i).getCountries().get(j).getHolder() == command.getPlayer().getColor()){
                    countries += 1;
                }
            }
        }
        return countries;
    }


}
