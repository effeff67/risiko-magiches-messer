package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Mission;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.util.CircularPlayerIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StartGameExec implements CommandExecutor {

    private final Game game;
    private final GameChangeRequest command;
    private final GameChangeResponse response;

    public StartGameExec(Game game, GameChangeRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        game.setStarted(true);
        Collections.shuffle(game.getPlayers());
        game.setActivePlayer(game.getPlayers().get(0).getColor());
        setMissions();
        setTroops();
        setRegions();
        /*
        int total = 0;
        for(int i = 0; i < game.getPlayers().size();i++){
            total = total + game.getPlayers().get(i).getInactiveTroops();
        }
        int j = game.getPlayers().size();
        for (int i = total - 1; i >= 0; i--){
            command.setPlayer(game.getPlayers().get(j));
            new PlaceTroopExec(game, command, response);
            j -= 1;
            if(j == 0){
                j = game.getPlayers().size();
            }
        }
         */
        return response;
    }

    private void setRegions() {
        List<Country> countries = new ArrayList<>();
        game.getGameMap().getContinentList().forEach(continent -> {
            continent.getCountries().forEach(country -> {
                countries.add(country);
            });
        });
        Collections.shuffle(countries);
        CircularPlayerIterator iterator = new CircularPlayerIterator(game.getPlayers());
        countries.forEach(country -> {
            Player current = iterator.next();
            country.setHolder(current.getColor());
            country.setTroopCount(1);
            current.setInactiveTroops(current.getInactiveTroops() - 1);
        });
    }

    private void setTroops() {
        int troopsCount = 0;
        switch (game.getPlayers().size()) {
            case 2:
            case 3: troopsCount =35;
                break;
            case 4:
                troopsCount = 30;
                break;
            case 5:
                troopsCount = 25;
                break;
        }
        for (int i = 0; i < game.getPlayers().size(); i++) {
            game.getPlayers().get(i).setInactiveTroops(troopsCount);
        }
    }

    private void setMissions() {
        List<Mission> missions = Arrays.asList(Mission.values());
        Collections.shuffle(missions);
        game.getPlayers().forEach(player -> player.setMission(missions.remove(0)));
    }
}
