package com.example.sportradarbetask.controllers;

import com.example.sportradarbetask.models.Dtos.EventDto;
import com.example.sportradarbetask.models.Dtos.SportDto;
import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Sport;
import com.example.sportradarbetask.service.SportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SportsController {

    private final SportService sportService;

    public SportsController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping("/api/sports")
    public ResponseEntity<?> getSports() {
        List<Sport> sportsCategories = sportService.getAllSportsCategories();
        List<SportDto> sportsCategoriesDtos = new ArrayList<>();

        for (Sport sport : sportsCategories) {
            sportsCategoriesDtos.add(sportService.toDto(sport));
        }
        // to Dto
        return ResponseEntity.ok(sportsCategoriesDtos);
    }
}
