package com.example.sportradarbetask.service;

import com.example.sportradarbetask.daos.utility.UtilityDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilityService {
    private UtilityDao utilityDao;

    public UtilityService(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }

    public List<String> getAllCountries() {
        return utilityDao.findCountries();
    }

    public List<String> getAllCities() {
        return utilityDao.findCities();
    }




}
