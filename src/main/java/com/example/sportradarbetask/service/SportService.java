package com.example.sportradarbetask.service;

import com.example.sportradarbetask.daos.sport.SportDao;
import com.example.sportradarbetask.models.Dtos.SportDto;
import com.example.sportradarbetask.models.Sport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {
    private final SportDao sportDao;

    public SportService(SportDao sportDao) {
        this.sportDao = sportDao;
    }

    public List<Sport> getAllSportsCategories() {
        return sportDao.findSportsCategories();
    }

    public SportDto toDto(Sport sport) {
        return new SportDto(sport.getName());
    }
}
