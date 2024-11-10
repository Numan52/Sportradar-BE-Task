package com.example.sportradarbetask.daos.venue;

import com.example.sportradarbetask.daos.venue.VenueDao;
import com.example.sportradarbetask.models.Venue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class VenueDaoImpl implements VenueDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Venue findById(Long id) {
        return entityManager.find(Venue.class, id);
    }
}
