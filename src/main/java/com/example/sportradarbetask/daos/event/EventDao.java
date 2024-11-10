package com.example.sportradarbetask.daos.event;

import com.example.sportradarbetask.models.Event;

import java.util.List;

public interface EventDao {
    Event findById(Long id);
    List<Event> findAll();
    void save(Event event);
    void update(Event event);
    void delete(Long id);
}
