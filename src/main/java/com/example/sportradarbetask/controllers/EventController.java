package com.example.sportradarbetask.controllers;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Dtos.EventDto;
import com.example.sportradarbetask.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<EventDto> eventDtos = new ArrayList<>();


        for (Event event : events) {
            eventDtos.add(eventService.toDto(event));
        }
        // to Dto
        return ResponseEntity.ok(eventDtos);
    }

    @PostMapping("api/events/addEvent")
    public ResponseEntity<?> addEvent(@RequestBody EventDto eventDto) {
        Event event = eventService.dtoToEvent(eventDto);
        System.out.println(event.toString());
        eventService.addEvent(event);
        return ResponseEntity.ok("Event was successfully added");
    }
}
