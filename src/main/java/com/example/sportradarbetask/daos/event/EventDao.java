package com.example.sportradarbetask.daos.event;

import com.example.sportradarbetask.models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventDao {
    List<Event> findBySportAndDate(String sport, LocalDate date);
    List<Event> findBySport(String sport);
    List<Event> findByDate(LocalDate date);
    Event findById(Long id);
    List<Event> findAll();
    void save(Event event);
    void update(Event event);
    void delete(Long id);
}
