package com.example.sportradarbetask.controllers;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.EventDto;
import com.example.sportradarbetask.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/api/events/getEvents")
    public ResponseEntity<?> getEvents() {
        List<Event> events = eventService.getAllEvents();
        System.out.println(events.get(0));
        // to Dto
        return ResponseEntity.ok(events);
    }

    @PutMapping("api/events/addEvent")
    public ResponseEntity<?> addEvent(@RequestBody EventDto eventDto) {
        Event event = eventService.dtoToEvent(eventDto);
        eventService.addEvent(event);
        return ResponseEntity.ok().build();
    }
}
