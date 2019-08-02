package edu.htwk.mm.risiko.controller;


import edu.htwk.mm.risiko.model.GameList;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.service.GameService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/games")
public class GameStartController {

    private GameService gameService;

    public GameStartController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping
    public ResponseEntity<GameList> getGames() {
        return ResponseEntity.ok(gameService.getOpenGames());
    }
    
    @GetMapping(path = "/{name}")
    public ResponseEntity<Game> getGame(@PathVariable("name") String name){
    	if(StringUtils.isEmpty(name)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	try {
    		Game game = gameService.getGame(name);
    		if(game != null) {
    			return ResponseEntity.ok(game);
    		}
    		else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    		}
    	}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
    
    @GetMapping(path = "/new/{name}/{username}/{color}")
    public ResponseEntity<Game> setnewGame(@PathVariable("name") String name,@PathVariable("username") String username,@PathVariable("color") String color){
    	if(StringUtils.isEmpty(name)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	try {
    		Game game = gameService.setnewGame(name, username, color);
    		if(game != null) {
    			return ResponseEntity.ok(game);
    		}
    		else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    		}
    	}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    
    } 
    
    @GetMapping(path = "/delete/{name}")
    public ResponseEntity<String> deleteGame(@PathVariable("name") String name){
    	if(StringUtils.isEmpty(name)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	try {
    		boolean delete = gameService.deleteGame(name);
    		if(delete) {
    			return ResponseEntity.ok("Game was deleted!");
    		}
    		else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    		}
    	}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}   
    }
    
    //TODO Funktioniert nicht - nochmal überprüfen
    @GetMapping(path = "/addPlayer/{name}/{username}/{color}")
    public ResponseEntity<Game> addPlayertoGame(@PathVariable("name") String name,@PathVariable("username") String username,@PathVariable("color") String color){
    	if(StringUtils.isEmpty(name)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	try {
    		Game game = gameService.addPlayer(name, username, color);
    		if(game != null) {
    			return ResponseEntity.ok(game);
    		}
    		else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    		}
    	}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    
    }
    
    //TODO: CHECK IF WORKS PROPERLY
    @GetMapping(path = "/removePlayer/{name}/{color}")
    public ResponseEntity<Game> removePlayerFromGame(@PathVariable("name") String name,@PathVariable("color") String color){
    	if(StringUtils.isEmpty(name)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	try {
    		Game game = gameService.removePlayer(name,  color);
    		if(game != null) {
    			return ResponseEntity.ok(game);
    		}
    		else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    		}
    	}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    
    }
    //TODO:CHECK IF WORKS PROPERLY
    @GetMapping(path = "/startGame/{name}")
    public ResponseEntity<Game> startGame(@PathVariable("name") String name){
    	if(StringUtils.isEmpty(name)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	try {
    		Game game = gameService.startGame(name);
    		if(game != null) {
    			return ResponseEntity.ok(game);
    		}
    		else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    		}
    	}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    
    }
    
}
    
    
