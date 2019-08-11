package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.validation.TradeCardsInValidator;

import java.util.Collections;

public class DrawCardExec implements CommandExecutor {

    private final Game game;
    private final Player player;
    private final GameChangeResponse response;

    public DrawCardExec(Game game, Player player, GameChangeResponse response) {
        this.game = game;
        this.player = player;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        Collections.shuffle(game.getCardsStack().getCards());
        player.getCards().add(game.getCardsStack().getCards().get(0));
        game.getCardsStack().getCards().remove(game.getCardsStack().getCards().size());
        return response.setMessage("Sie haben erfolreich erobert, Sie bekommen eine Karte.");
    }
}
