package com.example.sportradarbetask.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@IdClass(VenueId.class)
public class Venue {
    @Id
    private String name;

    @Id
    private String address;
    private Integer capacity;
    private String city;
    private String country;
    @OneToMany(mappedBy = "venue")
    private List<Event> events;

    public Venue() {}

    public Venue(String name, String address, Integer capacity, String city, String country) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.city = city;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
