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
        List<Sport> sports = sportService.getAllSportsCategories();
        List<String> sportsStrings = new ArrayList<>();

        for (Sport sport : sports) {
            sportsStrings.add(sport.getName());
        }
        // to Dto
        return ResponseEntity.ok(sportsStrings);
    }
}
