package com.example.tickets.controller;

import com.example.tickets.dto.TicketDto;
import com.example.tickets.model.Ticket;
import com.example.tickets.service.impl.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("createTickets")
    public ResponseEntity<String> addTicketsToEvent(@RequestParam UUID eventId, @RequestBody List<TicketDto> tickets) {
        ticketService.addTicketsToEvent(tickets, eventId);
        return new ResponseEntity<>("Tickets added.", HttpStatus.CREATED);
    }

    @GetMapping("/getTicket")
    public ResponseEntity<Ticket> getTicket(@RequestParam UUID ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @GetMapping("/getAllTickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> ticketList = ticketService.getAllTickets();
        return ResponseEntity.status(HttpStatus.OK).body(ticketList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteTicket")
    public ResponseEntity<String> deleteTicket(@RequestParam UUID ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.status(HttpStatus.OK).body("Ticket deleted successfully!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteAllTickets")
    public ResponseEntity<String> deleteAllTickets() {
        ticketService.deleteAllTickets();
        return ResponseEntity.status(HttpStatus.OK).body("Ticket deleted successfully!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("updateTicket")
    public ResponseEntity<Ticket> updateTicket(@RequestBody TicketDto ticketDto, @RequestParam UUID ticketId) {
        Ticket ticket = ticketService.updateTicket(ticketDto, ticketId);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);

    }

    @GetMapping("/getTicketsByEvent")
    public ResponseEntity<List<Ticket>> getTicketsByEventId(@RequestParam UUID eventId) {
        List<Ticket> events = ticketService.getTicketsByEventId(eventId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
