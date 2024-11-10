package com.example.sportradarbetask.daos.team;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Team;

public interface TeamDao {
    Team findById(Long id);
}
