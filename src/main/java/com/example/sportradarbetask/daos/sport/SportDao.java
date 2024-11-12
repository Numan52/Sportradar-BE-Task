package com.example.sportradarbetask.daos.sport;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Sport;
import com.example.sportradarbetask.models.Team;

public interface SportDao {
    void save(Sport sport);
    Sport findByName(String name);
}
