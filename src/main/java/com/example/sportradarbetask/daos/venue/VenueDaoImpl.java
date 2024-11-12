package com.example.sportradarbetask.daos.venue;

import com.example.sportradarbetask.daos.venue.VenueDao;
import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Venue;
import com.example.sportradarbetask.models.VenueId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class VenueDaoImpl implements VenueDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void save(Venue event) {
        entityManager.persist(event);
    }

    @Override
    public Venue findByNameAndAddress(String name, String address) {
        VenueId venueId = new VenueId(name, address);
        return entityManager.find(Venue.class, venueId);
    }
}
