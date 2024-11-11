package com.example.sportradarbetask.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Sport {


    @Id
    private String name;

    @OneToMany(mappedBy = "sport")
    private List<Team> teams;

    @OneToMany(mappedBy = "sport")
    private List<Event> events;

    public Sport() {
    }

    public Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
