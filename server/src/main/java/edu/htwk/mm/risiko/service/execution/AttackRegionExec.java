package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;

import java.util.*;

public class AttackRegionExec implements CommandExecutor {

    private Game game;
    private GameChangeRequest command;
    private GameChangeResponse response;

    public AttackRegionExec(Game game, GameChangeRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        Random rnd = new Random();
        int attack = Math.min(command.getCommandDetails().get(attacker).getTroopCount(), 3);
        int defense = Math.min(command.getCommandDetails().get(defender).getTroopCOunt(), 2);
        List<Integer> offensive = new ArrayList<Integer>();
        List<Integer> defensive = new ArrayList<Integer>();
        for(int i = 0; i < attack; i++){
            offensive.add(rnd.nextInt(7));
        }
        for(int i = 0; i < defense; i++){
            defensive.add(rnd.nextInt(7));
        }
        offensive.sort(Collections.reverseOrder());
        offensive.sort(Collections.reverseOrder());
        for(int i = 0; i < Math.min(attack, defense);i++){
            if(offensive.get(i) > defensive.get(i)){
                command.getCommandDetails().get(defender).setTroopCount(command.getCommandDetails().get(defender).getTroopCount() - 1);
            } else {
                command.getCommandDetails().get(attacker).setTroopCount(command.getCommandDetails().get(attacker).getTroopCount() - 1);
            }
            if(command.getCommandDetails().get(defender).getTroopCount() == 0){
                response.setMessage("Das Gebiet " + " konnte nicht eingenommen werden. Der Angreifer verliert " + " Truppen und der Verteidiger " + " Truppen.");
                return response;
            }
            if(command.getCommandDetails().get(defender).getTroopcount() == 0){
                command.getCommandDetails().get(defender).setHolder(command.getPlayer().getColor());
                command.getPlayer().setConquered(true);
                response.setMessage("Das Gebiet " + " wurde erfolgreich eingenommen. Der Angreifer verliert " + " Truppen und der Verteidiger " + " Truppen.");
                return response;
            }
        }
        return response;
    }
}
