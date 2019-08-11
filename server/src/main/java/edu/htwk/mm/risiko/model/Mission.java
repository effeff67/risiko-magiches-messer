package edu.htwk.mm.risiko.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum Mission {

	ELIMINATE_BLUE("Eliminate the blue player."),
	ELIMINATE_GREEN("Eliminate the green player."),
	ELIMINATE_RED("Eliminate the red player."),
	ELIMINATE_VIOLET("Eliminate the violet player."),
	ELIMINATE_YELLOW("Eliminate the yellow player."),

	OCCUPY_NA_AFRICA("Occupy Northern America and Africa."),
	OCCUPY_NA_AUSTRALIA("Occupy Northern America and Australia."),
	OCCUPY_ASIA_SA("Occupy Asia and Southern America."),
	OCCUPY_EUROPE_AUSTRALIA("Occupy Europe and Australia."),
	OCCUPY_ASIA_AFRICA("Occupy Asia and Africa."),

	CONQUER_THE_WORLD("Conquer the world."),
	CONQUER_24("Conquer 24 countries of your choice."),
	CONQUER_18("Conquer 18 countries of your choice and place at least 2 troops each.");

	private String name;
}
