package edu.htwk.mm.risiko.service;


import edu.htwk.mm.risiko.model.Color;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.GameList;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Player;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final CommandExecution commandExecution;

    public GameService(GameRepository gameRepository, CommandExecution commandExecution) {
        this.gameRepository = gameRepository;
		this.commandExecution = commandExecution;
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

    public Game addGame(String gameName, Player player, boolean conquerTheWorld) {

		Game newGame = new Game(gameName, player, conquerTheWorld);
		if(gameRepository.addGame(newGame)){
			return newGame;
    	}
    	return null;
    }

    public boolean deleteGame(Game game) {
    	if(!StringUtils.isEmpty(game)) {
    		return gameRepository.deleteGame(game);
    	}
    	return false;
    }

    public GameChangeResponse changeGame(String gameName, GameChangeRequest command) {
    	Game game = gameRepository.getGameByName(gameName);
    	if(null == game) {
    		return new GameChangeResponse(Status.ERROR, "game not found");
		}
    	GameChangeResponse response = commandExecution.prepare(command).validate(game).execute();
    	if(response.getStatus() == Status.SUCCESS) {
    		gameRepository.saveGame(game);
		}
    	return response;
	}
}
