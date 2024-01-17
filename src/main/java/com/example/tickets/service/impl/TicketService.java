package com.example.tickets.service.impl;

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

@Service
@Slf4j
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    public ResponseEntity<String> addTicketsToEvent(List<Ticket> ticketList, UUID eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);

        if (event == null) {
            System.out.println("e null");
        }

        for (Ticket ticket : ticketList) {
            ticket.setEvent(event);
            event.getTicketList().add(ticket);
        }

        eventRepository.save(event);

        return ResponseEntity.status(HttpStatus.OK).body("Bilete adăugate cu succes la eveniment");
    }

    public ResponseEntity<String> deleteTicket(UUID ticketId) {
        ticketRepository.deleteById(ticketId);
        return ResponseEntity.status( HttpStatus.OK).body("Ticket deleted successfully!");
    }

    public ResponseEntity<String> updateTicket(Ticket ticket, UUID ticketId) {
        Ticket existedTicket = ticketRepository.findById(ticketId).orElse(null);

        existedTicket.setEvent(existedTicket.getEvent());
        existedTicket.setTicketNumber(ticket.getTicketNumber());
        existedTicket.setPrice(ticket.getPrice());

        ticketRepository.save(existedTicket);
        return ResponseEntity.status( HttpStatus.OK).body("Ticket updated successfully!");
    }

    public List<Ticket> getTicketsByEventId(UUID eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);

        return event.getTicketList();
    }
}
