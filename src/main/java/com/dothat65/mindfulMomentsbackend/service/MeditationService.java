package com.dothat65.mindfulMomentsbackend.service;

import com.dothat65.mindfulMomentsbackend.model.Meditation;
import com.dothat65.mindfulMomentsbackend.repository.MeditationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeditationService {
    private final MeditationRepository meditationRepository;

    @Autowired
    public MeditationService(MeditationRepository meditationRepository) {
        this.meditationRepository = meditationRepository;
    }

    public List<Meditation> getAllMeditations() {
        return meditationRepository.findAll();
    }
}