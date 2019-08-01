package edu.htwk.mm.risiko.controller;

import edu.htwk.mm.risiko.model.GameMap;
import edu.htwk.mm.risiko.service.MapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/maps")
public class ContinentController {
	
	private MapService mapService;
	
	public ContinentController(MapService mapService) {
		this.mapService = mapService;
	}
	
	@GetMapping(path = "/{name}")
	public ResponseEntity<GameMap> getMap(@PathVariable("name") String name){
		if(StringUtils.isEmpty(name)){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		try {
			GameMap map = mapService.getMap(name);
			if (map != null) {
				return ResponseEntity.ok(map);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
