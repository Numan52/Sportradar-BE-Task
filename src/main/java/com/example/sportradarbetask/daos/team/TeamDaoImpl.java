package com.example.sportradarbetask.daos.team;

import com.example.sportradarbetask.daos.team.TeamDao;
import com.example.sportradarbetask.models.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class TeamDaoImpl implements TeamDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Team team) {
        entityManager.persist(team);
    }

    @Override
    public Team findById(Long id) {
        return entityManager.find(Team.class, id);
    }

    @Override
    public Team findByNameAndSport(String name, String sport) {
        try {
            return entityManager.createQuery(
                            "SELECT t FROM Team t WHERE t.name = :name AND t.sport.name = :sport", Team.class)
                    .setParameter("name", name)
                    .setParameter("sport", sport)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
