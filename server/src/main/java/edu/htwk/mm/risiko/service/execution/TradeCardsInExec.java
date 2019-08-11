package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;

public class TradeCardsInExec implements CommandExecutor {

    private Game game;
    private GameChangeRequest command;
    private GameChangeResponse response;

    public TradeCardsInExec(Game game, GameChangeRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;

    }
    @Override
    public GameChangeResponse execute() {
        command.getPlayer().getCards().getCards().remove(command.getCommandDetails().get(card1));
        command.getPlayer().getCards().getCards().remove(command.getCommandDetails().get(card2));
        command.getPlayer().getCards().getCards().remove(command.getCommandDetails().get(card3));
        switch(game.getSetCount()){
            case 1:
                command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() + 4);
                break;
            case 2:
                command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() + 6);
                break;
            case 3:
                command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() + 8);
                break;
            case 4:
                command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() + 10);
                break;
            case 5:
                command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() + 12);
                break;
            case 6:
                command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() + 15);
                break;
            default:
                command.getPlayer().setInactiveTroops(command.getPlayer().getInactiveTroops() + 15 + (5 * (game.getSetCount() - 6)));
        }
        game.setSetCount(game.getSetCount() + 1);
        response.setMessage("Karten erfolgreich eingetauscht.");
        return response;
    }
}
