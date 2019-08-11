package edu.htwk.mm.risiko.service;

import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.service.validation.*;
import org.springframework.stereotype.Service;

@Service
public class CommandExecution {

    public CommandValidator prepare(GameChangeRequest commandRequest) {
        switch (commandRequest.getCommand()) {
            case ADD_PLAYER: return new AddPlayerValidator(commandRequest);
            case ATTACK_REGION: return new AttackRegionValidator(commandRequest);
            case DRAW_CARD: return new DrawCardValidator(commandRequest);
            case MOVE_TROOPS: return new MoveTroopValidator(commandRequest);
            case MOVE_TROOP_INTO_OCCUPIED_COUNTRY: return new MoveTroopAfterOccupationValidation(commandRequest);
            case PLACE_TROOP: return new PlaceTroopValidator(commandRequest);
            case PLACE_TROOPS: return new PlaceTroopsValidator(commandRequest);
            case RECRUIT_TROOPS: return new RecruitTroopsValidator(commandRequest);
            case START_GAME: return new StartGameValidator(commandRequest);
            case TRADE_CARDS: return new TradeCardsInValidator(commandRequest);
        }
        return new InvalidCommandValidation();
    }
}
