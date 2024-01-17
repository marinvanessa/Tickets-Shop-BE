package com.example.tickets.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Data
public class EventDto {

    private UUID eventId;
    private String name;
    private String description;
    private LocalDate localDate;
    private List<TicketDto> ticketDTOList;
    private UUID locationId;

}
