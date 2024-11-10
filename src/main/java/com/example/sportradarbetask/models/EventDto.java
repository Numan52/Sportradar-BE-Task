package com.example.sportradarbetask.models;

import java.time.LocalDate;
import java.util.Set;

public class EventDto {
    private Long eventId;
    private LocalDate date;
    private String description;
    private int entranceFee;
    private Long venueId;
    private Set<Long> teamIds;

    public EventDto(Long eventId, LocalDate date, String description, int entranceFee, Long venueId, Set<Long> teamIds) {
        this.eventId = eventId;
        this.date = date;
        this.description = description;
        this.entranceFee = entranceFee;
        this.venueId = venueId;
        this.teamIds = teamIds;
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

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public Set<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(Set<Long> teamIds) {
        this.teamIds = teamIds;
    }
}
