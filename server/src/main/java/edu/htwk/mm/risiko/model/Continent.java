package edu.htwk.mm.risiko.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Continent {
	private String name;
	private String color;
	private int troopBonus;
	private List<Country> country;

}
