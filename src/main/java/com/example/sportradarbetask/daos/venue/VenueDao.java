package com.example.sportradarbetask.daos.venue;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Venue;

public interface VenueDao {
    void save(Venue venue);
    Venue findByNameAndAddress(String name, String address);
}
