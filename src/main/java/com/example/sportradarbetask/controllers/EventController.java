package com.example.sportradarbetask.controllers;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Dtos.EventDto;
import com.example.sportradarbetask.service.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

        System.out.println("eventdtos: " + eventDtos);

        return ResponseEntity.ok(eventDtos);
    }

    @GetMapping("/api/events/filter")
    public ResponseEntity<?> getFilteredEvents(
            @RequestParam(required = false) String sport,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Event> filteredEvents = new ArrayList<>();
        List<EventDto> filteredEventDtos = new ArrayList<>();

        if (sport != null && date != null) {
            filteredEvents = eventService.findBySportAndDate(sport, date);
        } else if (sport != null) {
            filteredEvents = eventService.findBySport(sport);
        } else if (date != null) {
            filteredEvents = eventService.findByDate(date);
        } else {
            filteredEvents = eventService.getAllEvents();
        }

        for (Event event : filteredEvents) {
            filteredEventDtos.add(eventService.toDto(event));
        }

        return ResponseEntity.ok(filteredEventDtos);
    }




    @PostMapping("api/events/addEvent")
    public ResponseEntity<?> addEvent(@RequestBody EventDto eventDto) {
        Event event = eventService.dtoToEvent(eventDto);
        System.out.println(event.toString());
        eventService.addEvent(event);
        return ResponseEntity.ok("Event was successfully added");
    }
}
