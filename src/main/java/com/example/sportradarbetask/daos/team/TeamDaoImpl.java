package com.example.sportradarbetask.daos.team;

import com.example.sportradarbetask.daos.team.TeamDao;
import com.example.sportradarbetask.models.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class TeamDaoImpl implements TeamDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Team findById(Long id) {
        return entityManager.find(Team.class, id);
    }
}
