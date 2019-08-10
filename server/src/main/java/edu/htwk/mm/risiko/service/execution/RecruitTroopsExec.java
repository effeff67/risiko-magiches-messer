package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.GameMap;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameCommandRequest;

public class RecruitTroopsExec implements CommandExecutor {

    private Game game;
    private GameChangeRequest command;
    private GameChangeResponse response;

    public RecruitTroopsExec(Game game, GameChangeRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        int countries = 0;
        for(int i = 0; i < game.getGameMap().getContinentList().size(); i++){
            for(int j = 0; j < game.getGameMap().getContinentList().get(i).getCountries().size(); j++){
                if(game.getGameMap().getContinentList().get(i).getCountries().get(j).getHolder() == command.getPlayer().getColor()){
                    countries += 1;
                }
            }
        }
        int continents = 0;
        for(int i = 0; i < game.getGameMap().getContinentList().size(); i++){
            boolean conquered = true;
            for (int j = 0; j < game.getGameMap().getContinentList().get(i).getCountries().size();j++) {
                if(game.getGameMap().getContinentList().get(i).getCountries().get(j).getHolder() == command.getPlayer().getColor()){
                    conquered = false;
                    break;
                }
            }
            if(conquered) {
                continents = continents + game.getGameMap().getContinentList().get(i).getTroopBonus();
            }
        }
        int bonus = (countries / 3) + continents;
        if (bonus < 3){
            bonus = 3;
        }
        command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() + bonus);

        return response;
    }
}
