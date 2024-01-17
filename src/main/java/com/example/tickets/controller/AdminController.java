package com.example.tickets.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AdminController {

    @GetMapping
    public ResponseEntity<String> syHi() {
        log.info("Sending user to be saved.");
        return ResponseEntity.ok("hi admin");
    }
}
