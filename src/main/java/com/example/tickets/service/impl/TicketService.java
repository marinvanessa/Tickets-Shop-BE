package com.example.tickets.service.impl;

import com.example.tickets.dto.TicketDto;
import com.example.tickets.model.Event;
import com.example.tickets.model.ShoppingCart;
import com.example.tickets.model.Ticket;
import com.example.tickets.repository.EventRepository;
import com.example.tickets.repository.ShoppingCartRepository;
import com.example.tickets.repository.TicketRepository;
import com.example.tickets.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

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
        return ResponseEntity.status(HttpStatus.OK).body("Ticket deleted successfully!");
    }

    @Transactional
    public void addTicketToCart(UUID userId, UUID ticketId) {
        if (userRepository.findById(userId).isPresent() && ticketRepository.findById(ticketId).isPresent()) {
            ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(userRepository.findById(userId).get());
            Ticket ticket = ticketRepository.findById(ticketId).get();
            ticket.setShoppingCart(shoppingCart);
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + ticket.getPrice());
            ticketRepository.save(ticket);
        }

    }

    @Transactional
    public void deleteTicketFromCart(UUID userId, UUID ticketId) {
        if (userRepository.findById(userId).isPresent() && ticketRepository.findById(ticketId).isPresent()) {
            ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(userRepository.findById(userId).get());
            Ticket ticket = ticketRepository.findById(ticketId).get();
            ticket.setShoppingCart(null);
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - ticket.getPrice() < 0 ?
                    0 : shoppingCart.getTotalPrice() - ticket.getPrice());
            ticketRepository.save(ticket);
        }

    }

    @Transactional
    public void deleteAllTicketsFromCart(UUID userId) {
        if (userRepository.findById(userId).isPresent()) {
            ShoppingCart shoppingCart = shoppingCartRepository
                    .findShoppingCartByUser(userRepository.findById(userId).get());
            shoppingCart.getTickets().stream().forEach(ticket ->
            {
                ticket.setShoppingCart(null);
                ticketRepository.save(ticket);
            });
        }

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
