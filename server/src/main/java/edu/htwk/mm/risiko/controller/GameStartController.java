package edu.htwk.mm.risiko.controller;


import edu.htwk.mm.risiko.model.GameList;
import edu.htwk.mm.risiko.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600L )
@RestController
@RequestMapping(path = "/games")
public class GameStartController {

    private GameService gameService;

    public GameStartController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping
    public ResponseEntity<GameList> getGames() {
        log.info("get games requested");
        return ResponseEntity.ok(gameService.getOpenGames());
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
