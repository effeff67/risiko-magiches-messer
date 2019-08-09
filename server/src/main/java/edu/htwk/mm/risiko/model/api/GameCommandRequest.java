package edu.htwk.mm.risiko.model.api;


import edu.htwk.mm.risiko.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameCommandRequest {
    private GameCommand command;
    private Player player;
    private Object commandObject;
}
