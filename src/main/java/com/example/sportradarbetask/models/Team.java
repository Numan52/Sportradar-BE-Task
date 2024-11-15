package com.example.sportradarbetask.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String name;
    private String city;
    private int foundingDate;

    @ManyToOne
    @JoinColumn(name = "_sport", nullable = false)
    private Sport sport;

    @ManyToMany(mappedBy = "teams")
    private List<Event> event;

    public Team() {
    }

    public Team(Long teamId, String name, String city, int foundingDate, Sport sport) {
        this.teamId = teamId;
        this.name = name;
        this.city = city;
        this.foundingDate = foundingDate;
        this.sport = sport;

    }

    public Team(String name, String city, int foundingDate, Sport sport) {
        this.name = name;
        this.city = city;
        this.foundingDate = foundingDate;
        this.sport = sport;

    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(int foundingDate) {
        this.foundingDate = foundingDate;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }
}
