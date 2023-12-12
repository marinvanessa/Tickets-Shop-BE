package com.example.tickets.controller;

import com.example.tickets.model.Event;
import com.example.tickets.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@Slf4j
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/createEvent")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event createdEvent = eventService.addEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity<String> deleteEvent(@RequestParam UUID eventId) {
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>("Event deleted successfully!", HttpStatus.OK);
    }

    @PutMapping("/updateEvent/{eventId}")
    public ResponseEntity<String> updateEvent(@RequestBody Event event, @RequestParam UUID eventId) {
        return eventService.updateEvent(event, eventId);
    }
}
