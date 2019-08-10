package edu.htwk.mm.risiko.service;

import edu.htwk.mm.risiko.model.api.GameChangeRequest;
import edu.htwk.mm.risiko.service.validation.AddPlayerValidator;
import edu.htwk.mm.risiko.service.validation.CommandValidator;
import edu.htwk.mm.risiko.service.validation.InvalidCommandValidation;
import edu.htwk.mm.risiko.service.validation.StartGameValidator;
import org.springframework.stereotype.Service;

@Service
public class CommandExecution {

    public CommandValidator prepare(GameChangeRequest commandRequest) {
        switch (commandRequest.getCommand()) {
            case ADD_PLAYER: return new AddPlayerValidator(commandRequest);
            case START_GAME: return new StartGameValidator(commandRequest);
        }
        return new InvalidCommandValidation();
    }
}
