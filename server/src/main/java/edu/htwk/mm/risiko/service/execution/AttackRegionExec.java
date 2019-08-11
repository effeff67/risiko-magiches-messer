package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Occupation;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AttackRegionExec implements CommandExecutor {

    private final Game game;
    private final Player player;
    private final Country source;
    private final Country target;
    private int troopCount;
    private final GameChangeResponse response;
    private Random rnd = new Random();


    public AttackRegionExec(Game game, Player player, Country source, Country target, int troopCount, GameChangeResponse response) {
        this.game = game;
        this.player = player;
        this.source = source;
        this.target = target;
        this.troopCount = troopCount;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        int offenceDiceCount = Math.min(troopCount, 3);
        int defenseDiceCount = Math.min(source.getTroopCount(), 2);
        int defenceLostCount = 0;
        int offenceLostCount = 0;

        List<Integer> offensive = new ArrayList<>();
        List<Integer> defensive = new ArrayList<>();
        for(int i = 0; i < offenceDiceCount; i++){
            offensive.add(rnd.nextInt(5) + 1);
        }
        for(int i = 0; i < defenseDiceCount; i++){
            defensive.add(rnd.nextInt(5) + 1);
        }
        offensive.sort(Collections.reverseOrder());
        defensive.sort(Collections.reverseOrder());

        for(int i = 0; i < Math.min(offenceDiceCount, defenseDiceCount) && troopCount > 0; i++){
            if(offensive.get(i) > defensive.get(i)){
                target.setTroopCount(target.getTroopCount() - 1);
                ++defenceLostCount;
            } else {
                source.setTroopCount(source.getTroopCount() - 1);
                ++offenceLostCount;
            }
            if(target.getTroopCount() == 0) break;
        }

        if(source.getTroopCount() > 0){
            response.setMessage(String.format("Das Gebiet %s konnte nicht eingenommen werden. Der Angreifer verliert %s Truppen und der Verteidiger %s Truppen.",
                    target.getRegion().getName(), offenceLostCount, defenceLostCount));
        } else {
            target.setHolder(player.getColor());
            game.setLastOccupation(new Occupation(player.getColor(), source.getRegion(), target.getRegion()));
            response.setMessage(String.format("Das Gebiet %s wurde erfolgreich eingenommen. Der Angreifer verliert %s Truppen und der Verteidiger %s Truppen.",
                    target.getRegion().getName(), offenceLostCount, defenceLostCount));
        }
        return response;
    }
}
