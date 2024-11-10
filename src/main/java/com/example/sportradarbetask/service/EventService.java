package com.example.sportradarbetask.service;

import com.example.sportradarbetask.daos.event.EventDao;
import com.example.sportradarbetask.daos.team.TeamDao;
import com.example.sportradarbetask.daos.venue.VenueDao;
import com.example.sportradarbetask.exceptions.ResourceNotFoundException;
import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.EventDto;
import com.example.sportradarbetask.models.Team;
import com.example.sportradarbetask.models.Venue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EventService {
    private final EventDao eventDao;
    private final VenueDao venueDao;
    private final TeamDao teamDao;

    public EventService(EventDao eventDao, VenueDao venueDao, TeamDao teamDao) {
        this.eventDao = eventDao;
        this.venueDao = venueDao;
        this.teamDao = teamDao;
    }

    public Event getEventById(Long id) {
        return eventDao.findById(id);
    }

    public List<Event> getAllEvents() {
        return eventDao.findAll();
    }

    public void addEvent(Event event) {
        eventDao.save(event);
    }

    public void updateEvent(Event event) {
        eventDao.update(event);
    }

    public void deleteEvent(Long id) {
        eventDao.delete(id);
    }

    public EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setEventId(event.getEventId());
        eventDto.setDate(eventDto.getDate());
        eventDto.setDescription(event.getDescription());
        eventDto.setEntranceFee(eventDto.getEntranceFee());
        eventDto.setVenueName(event.getVenue().getName());
        eventDto.setVenueAddress(event.getVenue().getAddress());

        Set<Long> teamIds = new HashSet<>();
        event.getTeams().forEach((team) -> teamIds.add(team.getTeamId()));
        eventDto.setTeamIds(teamIds);

        return eventDto;
    }

    public Event dtoToEvent(EventDto eventDto) {
        Event event = new Event();

        Venue venue = venueDao.findByNameAndAddress(eventDto.getVenueName(), eventDto.getVenueAddress());
        if (venue == null) {
            throw new ResourceNotFoundException("Venue was not found with name: " + eventDto.getVenueName());
        }

        List<Team> teams = new ArrayList<>();
        for (Long id : eventDto.getTeamIds()) {
            Team team = teamDao.findById(id);
            if (team == null) {
                throw new ResourceNotFoundException("Team not found with ID: " + id);
            }
            teams.add(team);
        }

        event.setEventId(eventDto.getEventId());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());
        event.setEntranceFee(eventDto.getEntranceFee());
        event.setVenue(venue);
        event.setTeams(teams);

        return event;
    }
}
