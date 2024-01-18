package com.example.tickets.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JwtAuthenticationResponse {

    private UUID userId;

    private String token;

    private String refreshToken;
}
