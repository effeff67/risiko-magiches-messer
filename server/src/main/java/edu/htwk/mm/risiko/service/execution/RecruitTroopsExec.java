package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Continent;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;

import java.util.List;

public class RecruitTroopsExec implements CommandExecutor {

    private final Game game;
    private final Player player;
    private final List<Continent> continentList;
    private final int countryCount;
    private final GameChangeResponse response;

    public RecruitTroopsExec(Game game, Player player, List<Continent> continentList, int countryCount, GameChangeResponse response) {
        this.game = game;
        this.player = player;
        this.continentList = continentList;
        this.countryCount = countryCount;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        int bonus = (countryCount / 3) + continentList.stream().map(Continent::getTroopBonus).reduce(0, (a, b) -> a + b);
        if (bonus < 3){
            bonus = 3;
        }
        player.setInactiveTroops(player.getInactiveTroops() + bonus);
        return response.setMessage("Truppen rekrutiert");
    }
}
