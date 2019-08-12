package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Cards;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.InvalidCommandExec;
import edu.htwk.mm.risiko.service.execution.TradeCardsInExec;
import edu.htwk.mm.risiko.service.util.GameEntityFinder;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class TradeCardsInValidator implements CommandValidator {

    private final GameChangeResponse response;
    private final GameChangeRequest command;

    public TradeCardsInValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse(Status.ERROR);
    }

    @Override
    public CommandExecutor validate(Game game) {
        List<Cards> cards = null;
        try {
            cards = (List<Cards>) command.getCommandDetails().get("cards");
        }catch (Exception e) {
            ;
        }
        if(CollectionUtils.isEmpty(cards)) {
            return new InvalidCommandExec(response.setMessage("keine Karten zum Umtauschen angegeben!"));
        }
        if(cards.size() != 3) {
            return new InvalidCommandExec(response.setMessage("Inkorrekte Anzahl von Karten zum Umtauschen angegeben!"));
        }
        if(!(checkCardEquality(cards) || checkCardDiversity(cards))) {
            return new InvalidCommandExec(response.setMessage("Inkorrekte Auswahl von Karten zum Umtauschen angegeben!"));
        }

        Player player = GameEntityFinder.findPlayerByColor(game, command.getPlayer().getColor());
        if(!cards.stream().allMatch( card -> player.getCards().stream().anyMatch(pc -> pc == card))) {
            return new InvalidCommandExec(response.setMessage("Du musst auch die Karte besitzen, die du tauschen willst!"));
        }
        return new TradeCardsInExec(game, player, cards, response.setStatus(Status.SUCCESS));
    }

    private boolean checkCardDiversity(List<Cards> cards) {
        List<Cards> mustHave = Arrays.asList(Cards.ARTILLERY, Cards.CAVALRY, Cards.INFANTERY);
        return mustHave.stream().allMatch(card -> cards.stream().anyMatch(pc -> pc == card || card == Cards.JOKER));
    }

    private boolean checkCardEquality(List<Cards> cards) {
        return cards.stream().allMatch( card -> cards.stream().allMatch(pc -> pc == card || card == Cards.JOKER));
    }
}
