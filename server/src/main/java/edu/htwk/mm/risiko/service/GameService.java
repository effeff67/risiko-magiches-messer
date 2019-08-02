package edu.htwk.mm.risiko.service;


import edu.htwk.mm.risiko.model.GameList;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameList getOpenGames() {
        return new GameList(gameRepository.getAllOpenGames());
    }
    
    public Game getGame(String name) {
    	if(!StringUtils.isEmpty(name)) {
    		return gameRepository.getGameByName(name);
    	}
    	return null;
    }
    
    public Game setnewGame(String gamename, String username, String color) {
    	//TODO: Check ob Farbe den Farben entspricht
    	if((!StringUtils.isEmpty(gamename))&&(!StringUtils.isEmpty(username))&&(!StringUtils.isEmpty(color))) {
    		return gameRepository.setNewGame(gamename, username, color);
    	}
    	return null;
    }
    
    public Game saveGame(Game game) {
    	if(!StringUtils.isEmpty(game)) {
    		return gameRepository.overwriteGame(game);
    	}
    	return null;
    }
    
    public boolean deleteGame(String name) {
    	if(!StringUtils.isEmpty(name)) {
    		return gameRepository.deleteGame(name);
    	}
    	return false;
    }
    
    public Game addPlayer(String name, String username, String color) {
    	//TODO: Check ob Farbe den Farben entspricht
    	if((!StringUtils.isEmpty(name))&&(!StringUtils.isEmpty(username))&&(!StringUtils.isEmpty(color))){
    		return gameRepository.addPlayer(name, username, color);
    	}
    	return null;
    }
    
    public Game removePlayer(String gameName, String username) {
    	if((!StringUtils.isEmpty(gameName))&&(!StringUtils.isEmpty(username))){
    		return gameRepository.removePlayer(gameName, username);
    	}
    	return null;
    }
    
    public Game startGame(String nameGame) {
    	if(!StringUtils.isEmpty(nameGame)) {
    		return gameRepository.startGame(nameGame);
    	}
    	return null;
    }




}
