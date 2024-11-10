package com.example.sportradarbetask.daos.venue;

import com.example.sportradarbetask.models.Venue;

public interface VenueDao {
    Venue findByNameAndAddress(String name, String address);
}
