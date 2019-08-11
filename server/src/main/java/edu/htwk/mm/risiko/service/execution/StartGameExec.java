package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.service.validation.PlaceTroopValidator;

import java.util.Collections;

import java.util.Collections;

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
        Collections.shuffle(game.getMissions());
        for(int i = 0; i < game.getPlayers().size(); i++){
            game.getPlayers().get(i).setMission(game.getMissions().get(i));
        }
        switch (game.getPlayers().size()) {
            case 3 :
                for (int i = 0; i < game.getPlayers().size(); i++) {
                    game.getPlayers().get(i).setInactiveTroops(35);
                }
                break;
            case 4:
                for (int i = 0; i < game.getPlayers().size(); i++) {
                    game.getPlayers().get(i).setInactiveTroops(30);
                }
                break;
            case 5:
                for (int i = 0; i < game.getPlayers().size(); i++) {
                    game.getPlayers().get(i).setInactiveTroops(25);
                }
                break;
            default:
        }
        Collections.shuffle(game.getGameMap().getContinentList());
        for(int i = 0; i < game.getPlayers().size(); i++) {
            for (int j = 0; j < game.getGameMap().getContinentList().size(); j++) {
                for (int k = 0; k < game.getGameMap().getContinentList().get(j).getCountries().size();k++) {
                    game.getGameMap().getContinentList().get(j).getCountries().get(k).setHolder(game.getPlayers().get(i).getColor());
                    game.getGameMap().getContinentList().get(j).getCountries().get(k).setTroopcount(1);
                    game.getPlayers().get(i).setInactiveTroops(game.getPlayers().get(i).getInactiveTroops() - 1);
                }
            }
        }
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
        return response;
    }
}
