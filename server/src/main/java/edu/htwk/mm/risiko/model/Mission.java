package edu.htwk.mm.risiko.model;

import java.util.List;
import java.util.Random;

public class Mission {
	private int countrys;
	private int troopsPerCountry;
	private String defeatColor;
	private List<Continent> continents;
	
	public Mission(String playerColor, List<String> allColor, boolean conquerWorld) {
		if(conquerWorld) {
			this.countrys=42;
		}
		else {
			Random rand = new Random();
			int karten = rand.nextInt(12);
			if(karten<5) {
				int n = rand.nextInt(allColor.size());
				while(allColor.get(n)==playerColor) n = rand.nextInt(allColor.size());
				this.defeatColor=allColor.get(n);
			}
			else if((4<karten)&&(karten<10)) {
				
			}
			else if(karten==11) {
				this.countrys=24;				
			}
			else {
				this.countrys=18;
				this.troopsPerCountry=2;
			}
		}
	}
	
}
