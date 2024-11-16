package com.example.sportradarbetask.controllers;

import com.example.sportradarbetask.daos.utility.UtilityDao;
import com.example.sportradarbetask.models.Dtos.SportDto;
import com.example.sportradarbetask.models.Sport;
import com.example.sportradarbetask.service.SportService;
import com.example.sportradarbetask.service.UtilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UtilityController {
    private final UtilityService utilityService;

    public UtilityController(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    @GetMapping("/api/utility/countries")
    public ResponseEntity<?> getCountries() {
        List<String> countries = utilityService.getAllCountries();

        return ResponseEntity.ok(countries);
    }
    @GetMapping("/api/utility/cities")
    public ResponseEntity<?> getCities() {
        List<String> cities = utilityService.getAllCities();

        return ResponseEntity.ok(cities);
    }
}
