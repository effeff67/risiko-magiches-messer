package edu.htwk.mm.risiko.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Region {

    ALASKA("Alaska"),
    ALBERTA("Alberta"),
    EAST_CANADA("Ostkanada"),
    EASTERN_STATES("Oststaaten"),
    GREENLAND("Grönland"),
    MIDDLE_AMERICA("Mittelamerika"),
    NORTH_WEST_TERRETORIES("Nordwest-Territorium"),
    ONTARIO("Ontario"),
    WESTERN_STATES("Weststaaten"),

    AFGHANISTAN("Afghanistan"),
    CHINA("China"),
    INDIA("Indien"),
    IRKUTSK("Irkutsk"),
    JAKUTIEN("Jakutien"),
    JAPAN("Japan"),
    KAMCHATKA("Kamtschatka"),
    MIDDLE_EAST("Naher-Osten"),
    MONGOLIA("Mongolei"),
    SIBIRIA("Sibirien"),
    SOUTHEAST_ASIA("Südostasien"),
    URAL("Ural"),

    GREAT_BRITAIN("Großbritannien"),
    ICELAND("Island"),
    NORTHERN_EUROPE("Nordeuropa"),
    RUSSIA("Russland"),
    SKANDINAVIA("Skandinavien"),
    SOUTHERN_EUROPE("Südeuropa"),
    WESTERN_EUROPE("Westeuropa"),

    ARGENTINA("Argentinien"),
    BRAZIL("Brasilien"),
    PERU("Peru"),
    VENEZUELA("Venezuela"),

    CENTRAL_AFRICA("Zentralafrika"),
    EASTERN_AFRICA("Ostafrika"),
    EGYPT("Ägypten"),
    MADAGASCAR("Madagaskar"),
    NORTHERN_AFRICA("Nordafrika"),
    SOUTHERN_AFRICA("Südafrika"),

    EASTERN_AUSTRALIA("Ostaustralien"),
    GUINEA("Neuguinea"),
    INDONESIA("Indonesien"),
    WESTERN_AUSTRALIA("Westaustralien")
    ;
    @JsonValue
    private String name;

}
