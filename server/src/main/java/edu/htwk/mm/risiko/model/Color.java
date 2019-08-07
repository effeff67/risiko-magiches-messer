package edu.htwk.mm.risiko.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {
    BLUE("blue"),
    GREEN("green"),
    RED("red"),
    VIOLET("violet"),
    YELLOW("yellow");

    @JsonValue
    private String colorName;
}
