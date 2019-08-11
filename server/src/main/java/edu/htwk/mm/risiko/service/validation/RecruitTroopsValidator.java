package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;
import edu.htwk.mm.risiko.service.execution.RecruitTroopsExec;

public class RecruitTroopsValidator implements CommandValidator  {

    GameChangeResponse response;
    GameChangeRequest command;

    public RecruitTroopsValidator(GameChangeRequest command) {
        this.command = command;
        this.response = new GameChangeResponse();
    }

    @Override
    public CommandExecutor validate(Game game) {
        // hat das AUfrüsten am Anfang der Runde eine Bedingung?
        return new RecruitTroopsExec(game, command, response);
    }
}
