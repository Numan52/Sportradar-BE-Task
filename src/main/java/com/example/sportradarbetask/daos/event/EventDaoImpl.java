package com.example.sportradarbetask.daos.event;

import com.example.sportradarbetask.models.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class EventDaoImpl implements EventDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Event findById(Long id) {
        return entityManager.find(Event.class, id);
    }

    @Override
    public List<Event> findAll() {
        return entityManager.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Event event) {
        entityManager.merge(event);
    }

    @Override
    @Transactional
    public void update(Event event) {
        entityManager.merge(event);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Event event = this.findById(id);
        if (event != null) {
            entityManager.remove(event);
        }
    }
}
