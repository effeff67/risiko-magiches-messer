package edu.htwk.mm.risiko.model;


import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Continent {

	private String name;
	private int troopBonus;
	private List<Country> countries;

}
