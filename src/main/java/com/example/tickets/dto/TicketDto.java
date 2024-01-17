package com.example.tickets.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class TicketDto {
    private UUID ticketId;
    private Long price;
    private Integer ticketNumber;
    private Boolean isAvailable;
    private UUID eventId;

}
