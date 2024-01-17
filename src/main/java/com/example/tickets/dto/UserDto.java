package com.example.tickets.dto;

import com.example.tickets.enums.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
