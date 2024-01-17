package com.example.tickets.service.impl;

import com.example.tickets.dto.EventDto;
import com.example.tickets.dto.TicketDto;
import com.example.tickets.model.Event;
import com.example.tickets.model.Ticket;
import com.example.tickets.repository.EventRepository;
import com.example.tickets.repository.LocationRepository;
import com.example.tickets.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

        List<TicketDto> ticketDtoList = eventDto.getTicketList();
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

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events;
    }

    public Event getEvent(UUID eventId) {
        return eventRepository.findById(eventId).get();
    }

    public void deleteEvent(UUID eventId) {
        eventRepository.deleteById(eventId);
    }

    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    public Event updateEvent(EventDto eventDto, UUID eventId) {
        Event exitedEvent = eventRepository.findById(eventId).orElse(null);


        List<Ticket> ticketList = eventDto.getTicketList().stream()
                .map(ticketDto -> {
                    Ticket ticket = new Ticket();
                    ticket.setTicketId(ticketDto.getTicketId());
                    ticket.setTicketNumber(ticketDto.getTicketNumber());
                    ticket.setPrice(ticketDto.getPrice());
                    ticket.setIsAvailable(ticketDto.getIsAvailable());
                    ticket.setEvent(exitedEvent);
                    ticketRepository.save(ticket);
                    return ticket;
                })
                .toList();

        exitedEvent.setName(eventDto.getName());
        exitedEvent.setDescription(eventDto.getDescription());
        exitedEvent.setDate(eventDto.getLocalDate());
        exitedEvent.setDate(eventDto.getLocalDate());

        eventRepository.save(exitedEvent);

        return exitedEvent;
    }
}
