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
    START_GAME("startGame");

    @JsonValue
    private String commandName;

}
