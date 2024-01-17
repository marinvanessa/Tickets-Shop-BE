package com.example.tickets.service.impl;

import com.example.tickets.dto.TicketDto;
import com.example.tickets.model.Event;
import com.example.tickets.model.Ticket;
import com.example.tickets.repository.EventRepository;
import com.example.tickets.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    public void addTicketsToEvent(List<TicketDto> ticketList, UUID eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);

        List<Ticket> tickets = ticketList.stream().map(ticketDto -> {
            Ticket ticket = new Ticket();
            ticket.setTicketId(ticketDto.getTicketId());
            ticket.setTicketNumber(ticketDto.getTicketNumber());
            ticket.setPrice(ticketDto.getPrice());
            ticket.setIsAvailable(ticketDto.getIsAvailable());
            ticket.setEvent(event);
            ticketRepository.save(ticket);
            return ticket;
        }).toList();

        eventRepository.save(event);
    }

    public ResponseEntity<String> deleteTicket(UUID ticketId) {
        ticketRepository.deleteById(ticketId);
        return ResponseEntity.status( HttpStatus.OK).body("Ticket deleted successfully!");
    }

    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicket(UUID ticketId) {
        return ticketRepository.findById(ticketId).get();
    }


    public Ticket updateTicket(TicketDto ticketDto, UUID ticketId) {
        Ticket existedTicket = ticketRepository.findById(ticketId).orElse(null);

        existedTicket.setEvent(existedTicket.getEvent());
        existedTicket.setTicketNumber(ticketDto.getTicketNumber());
        existedTicket.setPrice(ticketDto.getPrice());
        existedTicket.setIsAvailable(ticketDto.getIsAvailable());

        ticketRepository.save(existedTicket);
        return existedTicket;
    }

    public List<Ticket> getTicketsByEventId(UUID eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);

        return event.getTicketList();
    }
}
