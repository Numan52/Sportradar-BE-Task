package com.example.sportradarbetask.daos.utility;

import com.example.sportradarbetask.models.Sport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class UtilityDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<String> findCountries() {
        try {
            return entityManager.createQuery(
                    "SELECT DISTINCT v.country FROM Venue v ORDER BY v.country ASC ", String.class
            ).getResultList();
        }catch (Exception e) {
            System.out.println("Couldn't find countries");
            return List.of();
        }
    }
    public List<String> findCities() {
        try {
            return entityManager.createQuery(
                    "SELECT DISTINCT t.city FROM Team t ORDER BY t.city ASC ", String.class
            ).getResultList();
        }catch (Exception e) {
            System.out.println("Couldn't find cities");
            return List.of();
        }
    }

}
