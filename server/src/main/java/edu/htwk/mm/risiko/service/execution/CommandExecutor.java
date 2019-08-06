package edu.htwk.mm.risiko.service.execution;

import edu.htwk.mm.risiko.model.api.GameChangeResponse;

public interface CommandExecutor {
    GameChangeResponse execute();
}
