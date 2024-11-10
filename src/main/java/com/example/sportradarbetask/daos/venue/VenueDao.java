package com.example.sportradarbetask.daos.venue;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Venue;

public interface VenueDao {
    Venue findById(Long id);
}
