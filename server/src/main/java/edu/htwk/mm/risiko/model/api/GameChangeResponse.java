package edu.htwk.mm.risiko.model.api;

import edu.htwk.mm.risiko.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameChangeResponse {
    private Status status;
    private String message;
    private Object response;

    public GameChangeResponse(Status status) {
        this.status = status;
    }

    public GameChangeResponse(Status status, String message) {
        this.status = status;
        this.message = message;
    }
}
