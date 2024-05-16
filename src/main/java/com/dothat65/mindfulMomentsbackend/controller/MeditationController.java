package com.dothat65.mindfulMomentsbackend.controller;

import com.dothat65.mindfulMomentsbackend.model.Meditation;
import com.dothat65.mindfulMomentsbackend.service.MeditationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meditations")
public class MeditationController {
    private final MeditationService meditationService;

    @Autowired
    public MeditationController(MeditationService meditationService) {
        this.meditationService = meditationService;
    }

    @GetMapping
    public ResponseEntity<List<Meditation>> getAllMeditations() {
        List<Meditation> meditations = meditationService.getAllMeditations();
        return ResponseEntity.ok(meditations);
    }
}