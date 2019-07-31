package edu.htwk.mm.risiko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country {
	
	private String name;
	private String color;
	private int troops;
	private List<String> neighborCountrys;
	

}
