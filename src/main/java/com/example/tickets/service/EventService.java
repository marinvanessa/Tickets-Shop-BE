package com.example.tickets.service;

import com.example.tickets.model.Event;
import com.example.tickets.model.Location;
import com.example.tickets.model.Ticket;
import com.example.tickets.repository.EventRepository;
import com.example.tickets.repository.LocationRepository;
import com.example.tickets.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    public Event addEvent(Event event) {
        Location location = event.getLocation();

        Optional<Location> existingLocation = locationRepository.findById(location.getLocationId());

        if (existingLocation.isEmpty()) {
            locationRepository.save(location);
        }

        Event createdEvent = eventRepository.saveAndFlush(event);

        createdEvent.setLocation(location);

        List<Ticket> ticketList = createdEvent.getTicketList();
        ticketList.forEach(ticket -> {
            ticket.setEvent(createdEvent);
        });

        return eventRepository.save(createdEvent);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void deleteEvent(UUID eventId) {
        eventRepository.deleteById(eventId);
    }

    public ResponseEntity<String> updateEvent(Event event, UUID eventId) {
        Event exitedEvent = eventRepository.findById(eventId).orElse(null);

        List<Ticket> ticketList = event.getTicketList();
        ticketList.forEach(ticket -> {
            ticket.setEvent(exitedEvent);
        });
        exitedEvent.setTicketList(event.getTicketList());
        exitedEvent.setName(event.getName());
        exitedEvent.setDescription(event.getDescription());
        exitedEvent.setDate(event.getDate());

        eventRepository.save(exitedEvent);

        return ResponseEntity.status(HttpStatus.OK).body("Event updated successfully!");
    }
}
