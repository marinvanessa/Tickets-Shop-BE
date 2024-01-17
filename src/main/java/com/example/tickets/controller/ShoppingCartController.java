package com.example.tickets.controller;

import com.example.tickets.dto.ShoppingCartDto;
import com.example.tickets.dto.TicketDto;
import com.example.tickets.model.ShoppingCart;
import com.example.tickets.service.impl.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/user/getShoppingCart")
    public ResponseEntity<ShoppingCartDto> getShoppingCart(@RequestParam UUID userId) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(userId);
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setShoppingCartId(shoppingCart.getShoppingCartId());
        shoppingCartDto.setTotalPrice(shoppingCart.getTotalPrice());
        List<TicketDto> ticketDtoList = shoppingCart.getTickets().stream().map(ticket -> {
            TicketDto ticketDto = new TicketDto();
            ticketDto.setTicketId(ticket.getTicketId());
            ticketDto.setTicketNumber(ticket.getTicketNumber());
            ticketDto.setPrice(ticket.getPrice());
            ticketDto.setIsAvailable(ticket.getIsAvailable());
            return ticketDto;
        }).toList();
        shoppingCartDto.setTicketDtoList(ticketDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartDto);
    }
}
