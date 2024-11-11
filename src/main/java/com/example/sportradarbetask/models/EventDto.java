package com.example.sportradarbetask.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class EventDto {
    private Long eventId;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private int entranceFee;
    private String venueName;
    private String venueAddress;
    private Set<Long> teamIds;

    public EventDto(){};

    public EventDto(Long eventId, LocalDate date, String description, int entranceFee, String venueName, String venueAddress, Set<Long> teamIds, LocalTime time) {
        this.eventId = eventId;
        this.date = date;
        this.time = time;
        this.description = description;
        this.entranceFee = entranceFee;
        this.venueName = venueName;
        this.venueAddress = venueAddress;
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

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public Set<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(Set<Long> teamIds) {
        this.teamIds = teamIds;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
