package com.example.sportradarbetask.daos.team;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Team;

public interface TeamDao {
    void save(Team team);
    Team findById(Long id);
    Team findByNameAndSport(String name, String sport);
}
