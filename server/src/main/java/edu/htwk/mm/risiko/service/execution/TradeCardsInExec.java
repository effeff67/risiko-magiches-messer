package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Cards;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;

import java.util.List;

public class TradeCardsInExec implements CommandExecutor {

    private static int[] TROOP_COUNT = new int[] {4,6,8,10,12,15};

    private final Game game;
    private final Player player;
    private final List<Cards> cards;
    private GameChangeResponse response;

    public TradeCardsInExec(Game game, Player player, List<Cards> cards, GameChangeResponse response) {
        this.game = game;
        this.player = player;
        this.cards = cards;
        this.response = response;
    }

    @Override
    public GameChangeResponse execute() {
        cards.forEach(card -> player.getCards().remove(card));

        int troopCount = game.getTradeCounts() >= 6 ? (game.getTradeCounts() - 2) * 5 : TROOP_COUNT[game.getTradeCounts()];
        player.setInactiveTroops(player.getInactiveTroops() + troopCount);
        game.setTradeCounts(game.getTradeCounts() + 1);
        return response.setMessage("Karten erfolgreich eingetauscht.");
    }
}
