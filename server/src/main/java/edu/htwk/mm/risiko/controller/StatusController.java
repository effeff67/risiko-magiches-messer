package edu.htwk.mm.risiko.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/status")
public class StatusController {

    @GetMapping
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("OK");
    }
}
