package com.example.tickets.service.impl;

import com.example.tickets.model.ShoppingCart;
import com.example.tickets.model.User;
import com.example.tickets.repository.ShoppingCartRepository;
import com.example.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    public ShoppingCart getShoppingCart(UUID userId) {
        if (userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();

            return shoppingCartRepository.findShoppingCartByUser(user);
        }
        return null;
    }
}
