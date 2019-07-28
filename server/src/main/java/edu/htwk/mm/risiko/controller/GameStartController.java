package edu.htwk.mm.risiko.controller;


import edu.htwk.mm.risiko.model.GameList;
import edu.htwk.mm.risiko.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
