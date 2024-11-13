package com.example.sportradarbetask.daos.sport;

import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Sport;
import com.example.sportradarbetask.models.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class SportDaoImpl implements SportDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Sport sport) {
        entityManager.merge(sport);
    }

    @Override
    public Sport findByName(String name) {
        return entityManager.find(Sport.class, name);
    }

    @Override
    public List<Sport> findSportsCategories() {
        try {
            return entityManager.createQuery(
                    "SELECT DISTINCT s.name FROM Sport s", Sport.class
            ).getResultList();
        }catch (Exception e) {
            System.out.println("Couldn't find sports categories");
            return null;
        }

    }
}
