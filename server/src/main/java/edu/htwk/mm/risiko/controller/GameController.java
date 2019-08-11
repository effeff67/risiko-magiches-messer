package edu.htwk.mm.risiko.controller;


import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Status;
import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;
import edu.htwk.mm.risiko.service.GameService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600L)
@RestController
@RequestMapping(path = "/games")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        log.info("get games requested");
        return ResponseEntity.ok(gameService.getOpenGames());
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<Game> getGame(@PathVariable("name") String name) {
        if (StringUtils.isEmpty(name)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Game game = gameService.getGame(name);
            if (game != null) {
                return ResponseEntity.ok(game);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/{name}")
    public ResponseEntity<GameChangeResponse> openGame(@PathVariable("name") String gameName,
                                                       @RequestBody() GameChangeRequest request) {
        log.info("add game requested");
        try {
            AddGameDetails details = new AddGameDetails(request.getCommandDetails());
            Game game = gameService.addGame(gameName, request.getPlayer(), details.conquerTheWorld);
            if (game != null) {
                return ResponseEntity.ok(new GameChangeResponse(Status.SUCCESS));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GameChangeResponse(Status.ERROR, "Failed to create game"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @PostMapping(path = "/{name}")
    public ResponseEntity<GameChangeResponse> changeGame(@PathVariable("name") String gameName,
                                                         @RequestBody() GameChangeRequest gameCommand) {
        try {
            GameChangeResponse response = gameService.changeGame(gameName, gameCommand);
            if (response.getStatus() == Status.SUCCESS) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping(path = "/{name}")
    public ResponseEntity<String> deleteGame(@PathVariable("name") String name) {
        if (StringUtils.isEmpty(name)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Game game = gameService.getGame(name);
            boolean delete = gameService.deleteGame(game);
            if (delete) {
                return ResponseEntity.ok("Game was deleted!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Getter @Setter
    static class AddGameDetails {
        private boolean conquerTheWorld;

        public AddGameDetails(LinkedHashMap<String, Object> object) {
            conquerTheWorld = (Boolean) object.get("conquerTheWorld");
        }
    }
}


