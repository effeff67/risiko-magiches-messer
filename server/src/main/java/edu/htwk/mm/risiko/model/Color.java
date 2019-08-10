package edu.htwk.mm.risiko.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {
    BLUE("blau"),
    GREEN("grün"),
    RED("rot"),
    VIOLET("violett"),
    YELLOW("gelb");

    @JsonValue
    private String colorName;
}
