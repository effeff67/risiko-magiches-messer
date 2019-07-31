package edu.htwk.mm.risiko.controller;

import edu.htwk.mm.risiko.model.ContinentList;
import edu.htwk.mm.risiko.service.ContinentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/continents")
public class ContinentController {
	
	private ContinentService continentService;
	
	public ContinentController(ContinentService continentService) {
		this.continentService = continentService;
	}
	
	@GetMapping
	public ResponseEntity<ContinentList> getContints(){
		return ResponseEntity.ok(continentService.getContinents());
	}

}
