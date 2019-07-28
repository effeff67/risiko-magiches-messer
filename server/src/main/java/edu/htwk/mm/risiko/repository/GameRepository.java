package edu.htwk.mm.risiko.repository;

import edu.htwk.mm.risiko.model.Game;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
public class GameRepository {

    public List<Game> getAllOpenGames() {
        return Arrays.asList(
                new Game("Alex"),
                new Game("Berta"),
                new Game("Conny"),
                new Game("Dora"),
                new Game("Emil")
                );
    }
}
