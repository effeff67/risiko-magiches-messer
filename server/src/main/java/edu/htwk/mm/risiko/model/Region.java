package edu.htwk.mm.risiko.model;


import lombok.AllArgsConstructor;

@AllArgsConstructor
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
