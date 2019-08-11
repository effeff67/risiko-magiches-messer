package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.TradeCardsInExec;

public class TradeCardsInValidator implements CommandValidator {

    GameChangeResponse response;
    GameChangeRequest command;

    public TradeCardsInValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        response.setStatus(Status.ERROR);
        // todo Karten auf 3 gleiche / 3 unterschiedliche überprüfen
        response.setStatus(Status.SUCCESS);
        response.setMessage("Karten wurden erfolgreich gegen Truppen eingetauscht.");
        return new TradeCardsInExec(game, command, response);
    }
}
