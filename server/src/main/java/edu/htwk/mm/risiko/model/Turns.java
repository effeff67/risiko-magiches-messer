package edu.htwk.mm.risiko.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Turns {

    RECRUIT_TROOPS("recruitTroops"),
    ATTACK_REGION("attackRegion"),
    MOVE_TROOPS("moveTroops"),
    FINISH_TURN("finishTurn");

    @JsonValue
    private String turnName;
}
