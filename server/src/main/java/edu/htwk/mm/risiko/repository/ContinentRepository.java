package edu.htwk.mm.risiko.repository;

import edu.htwk.mm.risiko.model.Continent;
import edu.htwk.mm.risiko.model.Country;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ContinentRepository {

	public List<Continent> getAllContinents(){
		List<Country> nordAmerika =new ArrayList<>();
		List<Country> suedAmerika =new ArrayList<>();
		List<Country> europa =new ArrayList<>();
		List<Country> asien =new ArrayList<>();
		List<Country> africa =new ArrayList<>();
		List<Country> australien =new ArrayList<>();
		
		nordAmerika.add(new Country("Alaska",null,0,Arrays.asList("Nordwest-Territorium","Alberta","Kamtschatka")));
		nordAmerika.add(new Country("Nordwest-Territorium",null,0,Arrays.asList("Alaska","Alberta","Onatrio","Grönland")));
		nordAmerika.add(new Country("Grönland",null,0,null));
		nordAmerika.add(new Country("Alberta",null,0,null));
		nordAmerika.add(new Country("Ontario",null,0,null));
		nordAmerika.add(new Country("Ostkanada",null,0,null));
		nordAmerika.add(new Country("Weststaaten",null,0,null));
		nordAmerika.add(new Country("Oststaaten",null,0,null));
		nordAmerika.add(new Country("Mittelamerika",null,0,null));
		return Arrays.asList(
				new Continent("Nord-Amerika",null,5,nordAmerika),
				new Continent("Süd-Amerika",null,2,suedAmerika),
				new Continent("Europa",null,5,europa),
				new Continent("Asien",null,7,asien),
				new Continent("Afrika",null,3,africa),
				new Continent("Australien",null,2,australien)
				);		
	}
} 
