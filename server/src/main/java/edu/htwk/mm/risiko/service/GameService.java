package edu.htwk.mm.risiko.service;


import edu.htwk.mm.risiko.model.GameList;
import edu.htwk.mm.risiko.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameList getOpenGames() {
        return new GameList(gameRepository.getAllOpenGames());
    }




}
