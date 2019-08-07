package edu.htwk.mm.risiko.controller;

import edu.htwk.mm.risiko.model.GameMap;
import edu.htwk.mm.risiko.repository.GameMapRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600L)
@RestController
@RequestMapping(path = "/maps")
public class MapController {

    private final GameMapRepository mapRepository;

    public MapController(GameMapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @GetMapping
    public ResponseEntity<List<GameMap>> getMaps() {
        log.info("get maps called");
        return ResponseEntity.ok(mapRepository.getAllMaps());
    }
}
