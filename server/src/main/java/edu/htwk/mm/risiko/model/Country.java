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
	
	private Region region;
	private List<Region> neighborhood;
	private Color holder;

}
