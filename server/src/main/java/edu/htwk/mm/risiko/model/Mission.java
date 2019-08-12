package edu.htwk.mm.risiko.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Mission {

	ELIMINATE_BLUE("Eliminiere den blauen Spieler. Falls du der blaue Spieler bist oder der blaue Spieler nicht am Spiel teilnimmt, musst du stattdessen 24 Gebiete erobern."),
	ELIMINATE_GREEN("Eliminiere den grünen Spieler. Falls du der grüne Spieler bist oder der grüne Spieler nicht am Spiel teilnimmt, musst du stattdessen 24 Gebiete erobern."),
	ELIMINATE_RED("Eliminiere den roten Spieler. Falls du der rote Spieler bist oder der rote Spieler nicht am Spiel teilnimmt, musst du stattdessen 24 Gebiete erobern."),
	ELIMINATE_VIOLET(	"Eliminiere den violetten Spieler. Falls du der violette Spieler bist oder der violette Spieler nicht am Spiel teilnimmt, musst du stattdessen 24 Gebiete erobern."),
	ELIMINATE_YELLOW("Eliminiere den gelben Spieler. Falls du der gelbe Spieler bist oder der gelbe Spieler nicht am Spiel teilnimmt, musst du stattdessen 24 Gebiete erobern."),

	OCCUPY_NA_AFRICA("Erobere sowohl Nordamerika als auch Afrika."),
	OCCUPY_NA_AUSTRALIA("Erobere sowohl Nordamerika als auch Australien."),
	OCCUPY_ASIA_SA("Erobere sowohl Asien als auch Südamerika."),
	OCCUPY_EUROPE_AUSTRALIA("Erobere sowohl Europa als auch Australien."),
	OCCUPY_ASIA_AFRICA("Erobere sowohl Asien als auch Afrika."),

	CONQUER_24("Erobere 24 beliebige Gebiete."),
	CONQUER_18("Erobere 18 beliebige Gebiete und stationiere auf jedem Gebiet mindestens 2 Einheiten.");
	@JsonValue
	private String name;
}














