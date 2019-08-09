package edu.htwk.mm.risiko.model.api;


import edu.htwk.mm.risiko.model.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter
@Setter
public class GameChangeRequest {
    private GameCommand command;
    private Player player;
    private LinkedHashMap commandDetails;
}
