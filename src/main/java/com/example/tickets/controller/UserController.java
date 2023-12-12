package com.example.tickets.controller;

import com.example.tickets.dto.LoginDTO;
import com.example.tickets.model.User;
import com.example.tickets.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        log.info("Sending user to be saved.");
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PostMapping("/getUserByUsernameAndPassword")
    public ResponseEntity<User> addUser(@RequestBody LoginDTO loginDTO) {
        User user = userService.getUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (Objects.isNull(user)) {
            log.info("The User wasn't found.");
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> addUser(@RequestParam UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
