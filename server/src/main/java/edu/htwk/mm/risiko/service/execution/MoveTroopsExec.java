package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.model.api.GameChangeResponse;

public class MoveTroopsExec implements CommandExecutor {

    private final Game game;
    private final Country source;
    private final Country target;
    private final Integer movingTroopCount;
    private final GameChangeResponse response;

    public MoveTroopsExec(Game game, Country source, Country target, Integer movingTroopCount, GameChangeResponse response) {
        this.game = game;
        this.source = source;
        this.target = target;
        this.movingTroopCount = movingTroopCount;
        this.response = response;
    }


    @Override
    public GameChangeResponse execute() {
        source.setTroopCount(source.getTroopCount() - movingTroopCount);
        target.setTroopCount(target.getTroopCount() + movingTroopCount);
        response.setMessage(String.format("%s Truppen erfolgreich von %s nach %s bewegt.", movingTroopCount,
                source.getRegion().getName(),
                target.getRegion().getName()));
        return response;
    }
}
