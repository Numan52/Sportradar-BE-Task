package com.example.sportradarbetask.daos.venue;

import com.example.sportradarbetask.daos.venue.VenueDao;
import com.example.sportradarbetask.models.Venue;
import com.example.sportradarbetask.models.VenueId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class VenueDaoImpl implements VenueDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Venue findByNameAndAddress(String name, String address) {
        VenueId venueId = new VenueId(name, address);
        return entityManager.find(Venue.class, venueId);
    }
}
