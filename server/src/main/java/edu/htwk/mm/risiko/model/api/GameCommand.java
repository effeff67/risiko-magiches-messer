package edu.htwk.mm.risiko.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum GameCommand {

    ADD_GAME("addGame"),
    ADD_PLAYER("addPlayer"),
    ATTACK_REGION("attackRegion"),
    DRAW_CARD("drawCard"),
    MOVE_TROOPS("moveTroops"),
    PLACE_TROOP("placeTroop"),
    PLACE_TROOPS("placeTroops"),
    RECRUIT_TROOPS("recruitTroops"),
    START_GAME("startGame"),
    TRADE_CARDS("tradeCards");


    @JsonValue
    private String commandName;

}
