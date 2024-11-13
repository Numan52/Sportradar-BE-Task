package com.example.sportradarbetask.daos.event;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
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

    @Override
    public List<Event> findBySportAndDate(String sport, LocalDate date) {
        try {
            return entityManager.createQuery(
                            "SELECT e FROM Event e WHERE e.sport.name = :sport AND e.date = :date", Event.class)
                    .setParameter("sport", sport)
                    .setParameter("date", date)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("No Events found that match the filter.");
            return null;
        }
    }

    @Override
    public List<Event> findBySport(String sport) {
        try {
            return entityManager.createQuery(
                            "SELECT e FROM Event e WHERE e.sport.name = :sport", Event.class)
                    .setParameter("sport", sport)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("No Events found that match the filter.");
            return null;
        }
    }

    @Override
    public List<Event> findByDate(LocalDate date) {
        try {
            return entityManager.createQuery(
                            "SELECT e FROM Event e WHERE e.date = :date", Event.class)
                    .setParameter("date", date)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("No Events found that match the filter.");
            return null;
        }
    }
}
