package com.example.tickets.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class ShoppingCartDto {

    private UUID shoppingCartId;

    private Long totalPrice;

    private UUID userId;

    private List<TicketDto> ticketDtoList;
}
