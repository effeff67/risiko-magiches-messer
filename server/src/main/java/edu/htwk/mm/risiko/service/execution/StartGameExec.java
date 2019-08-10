package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;

import java.util.Collections;

public class StartGameExec implements CommandExecutor {

    private final Game game;
    private final GameCommandRequest command;
    private final GameChangeResponse response;

    public StartGameExec(Game game, GameCommandRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        game.setStarted(true);
        Collections.shuffle(game.getPlayers());
        game.setActivePlayer(game.getPlayers().get(0).getColor());
        for(int i = 0; i < game.getPlayers().size(); i++){
            // game.getPlayers().get(i).setMission();
            // todo missionen verteilbar machen (enum ergänzen)
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
        response.setStatus(Status.SUCCESS);
        return response;
    }
}
