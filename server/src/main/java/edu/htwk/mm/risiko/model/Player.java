package edu.htwk.mm.risiko.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
    private String name;
    private Color color;
	private Mission mission;
	private int inactiveTroops;
	private List<Cards> cards;
	private boolean conquered = false;

	public Player (String name, Color color) {
		this.name = name;
		this.color = color;
	}
}
