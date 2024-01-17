package com.example.tickets.controller;

import com.example.tickets.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/createUser")
//    public ResponseEntity<User> addUser(@RequestBody User user) {
//        log.info("Sending user to be saved.");
//        return ResponseEntity.ok(userService.addUser(user));
//    }
//
//    @PostMapping("/getUserByUsernameAndPassword")
//    public ResponseEntity<User> addUser(@RequestBody LoginDTO loginDTO) {
//        User user = userService.getUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
//        if (Objects.isNull(user)) {
//            log.info("The User wasn't found.");
//        }
//        return ResponseEntity.ok(user);
//    }
//
//    @DeleteMapping("/deleteUser")
//    public ResponseEntity<String> addUser(@RequestParam UUID id) {
//        userService.deleteUser(id);
//        return ResponseEntity.ok("User deleted successfully!");
//    }
}
