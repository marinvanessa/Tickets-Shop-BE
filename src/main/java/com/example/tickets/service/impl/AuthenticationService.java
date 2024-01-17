package com.example.tickets.service.impl;

import com.example.tickets.dto.JwtAuthenticationResponse;
import com.example.tickets.dto.RefreshTokenRequest;
import com.example.tickets.dto.SignInRequest;
import com.example.tickets.dto.SignUpRequest;
import com.example.tickets.enums.Role;
import com.example.tickets.model.ShoppingCart;
import com.example.tickets.model.User;
import com.example.tickets.repository.ShoppingCartRepository;
import com.example.tickets.repository.UserRepository;
import com.example.tickets.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final ShoppingCartRepository shoppingCartRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;


    public User sighUp(SignUpRequest signUpRequest) {

        User user = new User();

        user.setId(UUID.randomUUID());

        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);


        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setShoppingCartId(UUID.randomUUID());
        shoppingCart.setUser(user);
        shoppingCart.setTotalPrice(0L);
        shoppingCartRepository.save(shoppingCart);

        return user;
    }

    public JwtAuthenticationResponse signIn(SignInRequest sign) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sign.getEmail(),
                sign.getPassword()));


        var user = userRepository.findByEmail(sign.getEmail()).orElseThrow(() ->
                new IllegalArgumentException("Invalid email or password"));


        var jwt = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());


            return jwtAuthenticationResponse;
        }
        return null;

    }


}
