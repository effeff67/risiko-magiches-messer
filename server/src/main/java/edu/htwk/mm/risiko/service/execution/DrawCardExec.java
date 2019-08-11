package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.validation.TradeCardsInValidator;

import java.util.Collections;

public class DrawCardExec implements CommandExecutor {

    private Game game;
    private GameChangeRequest command;
    private GameChangeResponse response;

    public DrawCardExec(Game game, GameChangeRequest command, GameChangeResponse response) {
        this.game = game;
        this.command = command;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        Collections.shuffle(game.getCardsStack().getCards());
        command.getPlayer().getCards().getCards().add(game.getCardsStack().getCards().get(game.getCardsStack().getCards().size() - 1));
        game.getCardsStack().getCards().remove(game.getCardsStack().getCards().size());
        command.getPlayer().setConquered(false);
        if(command.getPlayer().getCards().getCards().size() > 5){
            new TradeCardsInValidator(command);
        }
        return response;
    }
}
