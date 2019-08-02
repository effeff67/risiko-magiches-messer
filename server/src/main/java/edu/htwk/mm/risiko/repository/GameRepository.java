package edu.htwk.mm.risiko.repository;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Mission;
import edu.htwk.mm.risiko.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.List;


@Repository
@Slf4j
public class GameRepository {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
    public List<Game> getAllOpenGames() {
        return Arrays.asList(
        		//TODO sinnvoll ausarbeiten (Idee: games-Ordner nach Spielen mit playing=false durchsuchen
                new Game("Alex", false,false ,Arrays.asList(),Arrays.asList()),
                new Game("Berta", true,false ,Arrays.asList(),Arrays.asList()),
                new Game("Conny", true,false ,Arrays.asList(),Arrays.asList()),
                new Game("Dora", false,false ,Arrays.asList(),Arrays.asList()),
                new Game("Emil", true,false ,Arrays.asList(),Arrays.asList())
                );
    }
    
    public Game getGameByName(String gameName) {
    	try {
    		File file = new File(String.format("games/%s.json", gameName));
    		if(file.exists()) {
    			return objectMapper.readValue(file, Game.class);
    		} else {
    			log.error("failed to find game file games/{}.json", gameName);
    		}
    		return null;
    	}catch (Exception e) {
    		log.error("failed to load game {}", gameName, e);
			return null;
    	}
    }
    
    public Game setNewGame(String name, String username, String color) {
    	try {
    		File file = new File(String.format("games/%s.json", name));
    		if(file.exists()) {
    			log.error("failed to create game due to already excisting games/{}.json", name);
    			return null;
    		} 
    		else {
    			Game newGame = new Game();
    			newGame.setName(name);
    			objectMapper.writeValue(new File("games/"+newGame.getName()+".json"),newGame);
    			addPlayer(name, username, color);
    			return newGame;
    		}
    	}catch (Exception e) {
    		log.error("failed to create game {}", name, e);
    		return null;
    	}
    }
    
    public Game overwriteGame(Game game) {
    	try {
    		File file = new File(String.format("games/%s.json", game.getName()));
    		if(file.exists()) {
    			if(file.delete()) {
    				objectMapper.writeValue(new File("games/"+game.getName()+".json"),game);
    				return game;
    			}
    			log.error("failed to delet old game {}", game.getName());
    			return null;
    		} 
    		else {
    			log.error("failed to find game file games/{}.json", game.getName());
    			return null;
    		}
    	}catch (Exception e) {
    		log.error("failed to overwrite game {}", game.getName(), e);
    		return null;
    	}
    }
    
    public boolean deleteGame(String name) {
    	try {
    		File file = new File(String.format("games/%s.json", name));
    		if(file.exists()) {
    			file.delete();
    			return true;
    		} 
    		else {
    			log.error("failed to find game file games/{}.json", name);
    			return false;
    		}
    	}catch (Exception e) {
    		log.error("failed to delet game {}", name, e);
    		return false;
    	}
    }
    
    public Game addPlayer(String gameName,String name, String color) {   	
    	Game game = getGameByName(gameName);
    	try{//TODO Überprüfung ob Farbe bereits im Spiel ist
        	game.getAllPlayers().add(new Player(name, color));
        	game.getColorsInGame().add(color);
        	//TODO add(something)doesn't work -> leads to error
        	overwriteGame(game);
        	return game;
    	}catch (Exception e) {
    		log.error("failed to add player to game {}", gameName, e);
    		return null;
    	}
    }
    
    public Game removePlayer(String gameName,String color) {
    	Game game = getGameByName(gameName);
    	try {for(int i=0; i<game.getAllPlayers().size();i++) {
    			if (game.getAllPlayers().get(i).getColor()==color) {
    				for(int j=0; j<game.getColorsInGame().size();j++) {
    					if(game.getColorsInGame().get(j)==color) {
    						game.getColorsInGame().remove(j);
    					}
    				}
    				game.getAllPlayers().remove(i);
    				overwriteGame(game);
    				return game;
    			}
    		}
    		log.error("failed due to unvalid color {}",color);
    		return null;
    	}catch (Exception e) {
    		log.error("failed to remove player from game {}", game.getName(), e);
    		return null;
    	}
    }
    
    public Game startGame(String gameName) {
    	Game game = getGameByName(gameName);
    	List<Player> tempPlayers = game.getAllPlayers();
    	int numPlayers = tempPlayers.size();
    	if ((3<numPlayers)&&(numPlayers<5)) {
    		try {
    	    	int troops = 0;
    	    	if(numPlayers==3) troops = 35;
    	    	else if(numPlayers==4) troops = 30;
    	    	else if(numPlayers==5) troops = 25;    	    	
    			for(int i=0; i<numPlayers;i++) {
    				tempPlayers.get(i).setMission(new Mission(tempPlayers.get(i).getColor(), game.getColorsInGame(), game.isConquerWorld()));
    				tempPlayers.get(i).setOpenTroops(troops);
    			}
    			//TODO: Adding Countrys to players
    			//TODO: Adding Cards to Game
    			game.setPlaying(true);
    			overwriteGame(game);
    			return game;  			
    		}catch (Exception e) {
        		log.error("failed to setup game {}", gameName, e);
        		return null;
        	}
    	}
    	log.error("failed due to not enough/to many players at game {}",gameName);
    	return null;	
    }
}
