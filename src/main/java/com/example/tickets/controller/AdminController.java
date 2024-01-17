package com.example.tickets.controller;

import com.example.tickets.convertor.Convertor;
import com.example.tickets.dto.UserDto;
import com.example.tickets.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@Slf4j
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private Convertor convertor;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(convertor.mapUserToUserDto(userService.getUserById(id)));
    }
}
