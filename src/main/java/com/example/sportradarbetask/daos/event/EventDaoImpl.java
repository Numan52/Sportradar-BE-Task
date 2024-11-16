package com.example.sportradarbetask.daos.event;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Sport;
import com.example.sportradarbetask.models.Team;
import com.example.sportradarbetask.models.Venue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        //  Easier with JPQL Query:
        //  return entityManager.createQuery("SELECT e FROM Event e", Event.class).getResultList();

        List<Event> events = new ArrayList<>();

        String sql = "SELECT ev.event_id AS event_id, ev.date, ev.time, ev.description, ev.entrance_fee, sp.name AS sport_name, " +
                            "v.name AS venue_name, v.address AS venue_address, v.city, v.country, v.capacity, " +
                            "t1.team_id AS teamA_id, t1.name AS teamA_name, " +
                            "t2.team_id AS teamB_id, t2.name AS teamB_name " +
                     "FROM event ev " +
                     "JOIN venue v ON ev._venue_name = v.name AND ev._venue_address = v.address " +
                      // One Join for each team
                     "JOIN event_team et1 ON ev.event_id = et1._event_id " +
                     "JOIN event_team et2 ON ev.event_id = et2._event_id AND et1._team_id < et2._team_id " +
                      // One Join for each team
                     "JOIN team t1 ON et1._team_id = t1.team_id " +
                     "JOIN team t2 ON et2._team_id = t2.team_id " +

                     "JOIN sport sp ON ev._sport = sp.name " +
                     "ORDER BY ev.date ASC, ev.time ASC ";

        List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();

        for (Object[] row : result) {
            Event event = new Event();
            Venue venue = new Venue();
            List<Team> teams = new ArrayList<>();

            event.setEventId((Long) row[0]);
            event.setDate(((Date) row[1]).toLocalDate());
            event.setTime(((Time) row[2]).toLocalTime());
            event.setDescription((String) row[3]);
            event.setEntranceFee((int) row[4]);
            event.setSport(new Sport((String) row[5]));

            venue.setName((String) row[6]);
            venue.setAddress((String) row[7]);
            venue.setCity((String) row[8]);
            venue.setCountry((String) row[9]);
            venue.setCapacity((int) row[10]);

            teams.add(new Team((Long) row[11], (String) row[12]));
            teams.add(new Team((Long) row[13], (String) row[14]));

            event.setVenue(venue);
            event.setTeams(teams);

            events.add(event);
        }

        return events;
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
