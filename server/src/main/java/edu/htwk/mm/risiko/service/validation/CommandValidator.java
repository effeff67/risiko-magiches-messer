package edu.htwk.mm.risiko.service.validation;

import edu.htwk.mm.risiko.model.Game;
import edu.htwk.mm.risiko.service.execution.CommandExecutor;

public interface CommandValidator {
    CommandExecutor validate(Game game);
}
