package com.example.tickets.service.impl;

import com.example.tickets.dto.EventDto;
import com.example.tickets.dto.TicketDto;
import com.example.tickets.model.Event;
import com.example.tickets.model.Location;
import com.example.tickets.model.Ticket;
import com.example.tickets.repository.EventRepository;
import com.example.tickets.repository.LocationRepository;
import com.example.tickets.repository.TicketRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private TicketRepository ticketRepository;

    public Event addEvent(EventDto eventDto) {
        Event event = new Event();
        event.setEventId(eventDto.getEventId());
        event.setName(eventDto.getName());
        event.setDate(eventDto.getLocalDate());
        event.setDescription(eventDto.getDescription());

        locationRepository.findById(eventDto.getLocationId()).ifPresent(event::setLocation);

        List<TicketDto> ticketDtoList = eventDto.getTicketDTOList();
        List<Ticket> ticketList = ticketDtoList.stream()
                .map(ticketDto -> {
                    Ticket ticket = new Ticket();
                    ticket.setTicketId(ticketDto.getTicketId());
                    ticket.setTicketNumber(ticketDto.getTicketNumber());
                    ticket.setPrice(ticketDto.getPrice());
                    ticket.setIsAvailable(ticketDto.getIsAvailable());
                    ticket.setEvent(event);
                    return ticket;
                })
                .toList();

        event.setTicketList(ticketList);

        return eventRepository.save(event);
    }

    @Transactional
    public List<Event> getAllEvents() {
        log.info("Fetching all events");
        List<Event> events = eventRepository.findAll();
        log.info("Fetched {} events", events.size());
        return events;
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
