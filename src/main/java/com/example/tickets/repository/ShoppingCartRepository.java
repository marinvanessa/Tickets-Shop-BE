package com.example.tickets.repository;

import com.example.tickets.model.ShoppingCart;
import com.example.tickets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {

    ShoppingCart findShoppingCartByUser(User user);
}
