package edu.htwk.mm.risiko.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Region {

    ALASKA("Alaska"),
    ALBERTA("Alberta"),
    NORTH_WEST_TERRETORIES("Nordwest-Territorium"),
    GREENLAND("Grönland"),
    ONTARIO("Ontario"),
    EAST_CANADA("Ostkanada"),
    WESTERN_STATES("Weststaaten"),
    EASTERN_STATES("Oststaaten"),
    MIDDLE_AMERICA("Mittelamerika"),

    KAMCHATKA("Kamtschatka"),

    ICELAND("Island"),

    VENEZUELA("Venezuela")
    ;

    private String name;

}
