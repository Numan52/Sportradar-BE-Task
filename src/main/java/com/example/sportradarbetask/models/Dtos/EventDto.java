package com.example.sportradarbetask.models.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventDto {
    private Long eventId;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private int entranceFee;
    private VenueDto venue;

    private List<TeamDto> teams;
    private String sport;


    public EventDto(){};

    public EventDto(Long eventId, LocalDate date, String description, int entranceFee, VenueDto venue, List<TeamDto> teams,
                    LocalTime time, String sport) {
        this.eventId = eventId;
        this.date = date;
        this.time = time;
        this.description = description;
        this.entranceFee = entranceFee;
        this.venue = venue;
        this.teams = teams;
        this.sport = sport;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEntranceFee() {
        return entranceFee;
    }

    public void setEntranceFee(int entranceFee) {
        this.entranceFee = entranceFee;
    }



    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public VenueDto getVenue() {
        return venue;
    }

    public void setVenue(VenueDto venue) {
        this.venue = venue;
    }

    public List<TeamDto> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDto> teams) {
        this.teams = teams;
    }
}
