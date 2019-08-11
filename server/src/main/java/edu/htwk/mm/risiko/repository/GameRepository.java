package edu.htwk.mm.risiko.repository;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.Mission;
import edu.htwk.mm.risiko.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@Slf4j
public class GameRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Game> getAllOpenGames() {
        File directory = new File("games");
        if (directory.isDirectory()) {
            return Arrays.stream(directory.listFiles((dir, filename) -> filename.endsWith(".json")))
                    .map(file -> {
                        try {
                            Game game = objectMapper.readValue(file, Game.class);
                            if (!game.isStarted()) {
                                return game;
                            }
                        } catch (IOException e) {
                            log.error("{} contains not a valid game", file.getName(), e);
                        }
                        return null;
                    })
                    .filter(game -> null != game)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Game getGameByName(String gameName) {
        try {
            File file = new File(String.format("games/%s.json", gameName.replaceAll("\\s+", "-")));
            if (file.exists()) {
                return objectMapper.readValue(file, Game.class);
            } else {
                log.error("failed to find game file games/{}.json", gameName);
            }
            return null;
        } catch (Exception e) {
            log.error("failed to load game {}", gameName, e);
            return null;
        }
    }

    public boolean addGame(Game game) {
        try {
            String gameFileName = String.format("games/%s.json", game.getName().replaceAll("\\s+", "-"));
            File file = new File(gameFileName);
            if (!file.exists()) {
                objectMapper.writeValue(file, game);
                return true;
            } else {
                log.error("failed to create game due to games/{}.json exists already", game.getName());
            }
        } catch (Exception e) {
            log.error("failed to create game {}", game.getName(), e);
        }
        return false;
    }

    public boolean saveGame(Game game) {
        try {
            String gameFileName = String.format("games/%s.json", game.getName().replaceAll("\\s+", "-"));
            File file = new File(gameFileName);
            if (file.exists()) {
                if (file.delete()) {
                    objectMapper.writeValue(file, game);
                    return true;
                }
                log.error("failed to delete old game {}", game.getName());
            } else {
                log.error("failed to find game file games/{}.json", game.getName());
            }
        } catch (Exception e) {
            log.error("failed to overwrite game {}", game.getName(), e);
        }
        return false;
    }

    public boolean deleteGame(Game game) {
        try {
            String gameFileName = String.format("games/%s.json", game.getName().replaceAll("\\s+", "-"));
            File file = new File(gameFileName);
            if (file.exists()) {
                file.delete();
                return true;
            } else {
                log.error("failed to find game file games/{}.json", game.getName());
                return false;
            }
        } catch (Exception e) {
            log.error("failed to delet game {}", game.getName(), e);
            return false;
        }
    }
}
