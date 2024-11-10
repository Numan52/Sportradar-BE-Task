package com.example.sportradarbetask.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private LocalDate date;
    private String description;
    private int entranceFee;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "_venue_name", referencedColumnName = "name", nullable = false),
            @JoinColumn(name = "_venue_address", referencedColumnName = "address", nullable = false)
    })  // an event must have one and only one venue
    private Venue venue;

    @ManyToMany
    @JoinTable(
            name = "event_team",
            joinColumns = @JoinColumn(name = "_event_id"),
            inverseJoinColumns = @JoinColumn(name = "_team_id")
    )
    private List<Team> team;


    public Event() {
    }

    public Event(Long eventId, LocalDate date, String description, int entranceFee, Venue venue, List<Team> team) {
        this.eventId = eventId;
        this.date = date;
        this.description = description;
        this.entranceFee = entranceFee;
        this.venue = venue;
        this.team = team;
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

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }
}
