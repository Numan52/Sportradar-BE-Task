package com.example.sportradarbetask.service;

import com.example.sportradarbetask.daos.event.EventDao;
import com.example.sportradarbetask.daos.sport.SportDao;
import com.example.sportradarbetask.daos.team.TeamDao;
import com.example.sportradarbetask.daos.venue.VenueDao;
import com.example.sportradarbetask.exceptions.ResourceNotFoundException;
import com.example.sportradarbetask.models.*;
import com.example.sportradarbetask.models.Dtos.EventDto;
import com.example.sportradarbetask.models.Dtos.TeamDto;
import com.example.sportradarbetask.models.Dtos.VenueDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventDao eventDao;
    private final VenueDao venueDao;
    private final TeamDao teamDao;
    private final SportDao sportDao;

    public EventService(EventDao eventDao, VenueDao venueDao, TeamDao teamDao, SportDao sportDao) {
        this.eventDao = eventDao;
        this.venueDao = venueDao;
        this.teamDao = teamDao;
        this.sportDao = sportDao;
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

    public List<Event> findBySportAndDate(String sport, LocalDate date) {
        return eventDao.findBySportAndDate(sport, date);
    }

    public List<Event> findBySport(String sport) {
        return eventDao.findBySport(sport);
    }

    public List<Event> findByDate(LocalDate date) {
        return eventDao.findByDate(date);
    }

    public EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();

        eventDto.setEventId(event.getEventId());
        eventDto.setDate(event.getDate());
        eventDto.setTime(event.getTime());
        eventDto.setDescription(event.getDescription());
        eventDto.setEntranceFee(event.getEntranceFee());

        VenueDto venueDto = new VenueDto(
                event.getVenue().getName(),
                event.getVenue().getAddress(),
                event.getVenue().getCity(),
                event.getVenue().getCountry(),
                event.getVenue().getCapacity());
        eventDto.setVenue(venueDto);

        List<TeamDto> teamDtos = new ArrayList<>();
        event.getTeams().forEach((team -> {
            teamDtos.add(new TeamDto(team.getTeamId(), team.getName(), team.getCity(), team.getFoundingDate()));
        }));
        eventDto.setTeams(teamDtos);

        eventDto.setSport(event.getSport().getName());



        return eventDto;
    }

    public Event dtoToEvent(EventDto eventDto) {
        Event event = new Event();

        Venue venue = venueDao.findByNameAndAddress(eventDto.getVenue().getName(), eventDto.getVenue().getAddress());
        if (venue == null) {
             venue = new Venue(
                    eventDto.getVenue().getName(),
                    eventDto.getVenue().getAddress(),
                    eventDto.getVenue().getCapacity(),
                    eventDto.getVenue().getCity(),
                    eventDto.getVenue().getCountry()
            );
             venueDao.save(venue);
        }


        Sport sport = sportDao.findByName(eventDto.getSport());
        if (sport == null) {
            sport = new Sport(eventDto.getSport().toLowerCase());
            sportDao.save(sport);
        }

        List<Team> teams = new ArrayList<>();

        for (TeamDto teamDto : eventDto.getTeams()) {
            Team team = teamDao.findByNameAndSport(teamDto.getTeamName(), sport.getName());
            if (team == null) {
                team = new Team(teamDto.getTeamName().toLowerCase(), teamDto.getCity().toLowerCase(), teamDto.getFoundingYear(), sport);
                teamDao.save(team);
            }
            teams.add(team);
        }


        event.setEventId(eventDto.getEventId());
        event.setDate(eventDto.getDate());
        event.setTime(eventDto.getTime());
        event.setDescription(eventDto.getDescription());
        event.setEntranceFee(eventDto.getEntranceFee());
        event.setSport(sport);
        event.setVenue(venue);
        event.setTeams(teams);

        return event;

    }
}
