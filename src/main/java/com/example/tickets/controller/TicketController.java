package com.example.tickets.controller;

import com.example.tickets.model.Event;
import com.example.tickets.model.Ticket;
import com.example.tickets.service.EventService;
import com.example.tickets.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
@Slf4j
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/{eventId}/tickets")
    public ResponseEntity<String> addTicketsToEvent(@PathVariable UUID eventId, @RequestBody List<Ticket> tickets) {
        return ticketService.addTicketsToEvent(tickets, eventId);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> deleteTicket(@RequestParam UUID ticketId) {
        return ticketService.deleteTicket(ticketId);
    }

    @PutMapping("updateTicket/{ticketId}")
    public ResponseEntity<String> updateTicket(@RequestBody Ticket ticket, @RequestParam UUID ticketId) {
        return ticketService.updateTicket(ticket, ticketId);
    }

    @GetMapping("/getTicketsByEvent/{eventId}")
    public ResponseEntity<List<Ticket>> getTicketsByEventId(@RequestParam UUID eventId) {
        List<Ticket> events = ticketService.getTicketsByEventId(eventId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
