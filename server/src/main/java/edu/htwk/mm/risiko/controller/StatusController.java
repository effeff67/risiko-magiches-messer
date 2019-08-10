package edu.htwk.mm.risiko.controller;


import edu.htwk.mm.risiko.model.api.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600L)
@RestController
@RequestMapping(path = "/status")
public class StatusController {

    @GetMapping
    public ResponseEntity<GenericResponse> getStatus() {
        return ResponseEntity.ok(new GenericResponse("OK"));
    }
}
