package com.dothat65.mindfulMomentsbackend.service;

import com.dothat65.mindfulMomentsbackend.model.Journal;
import com.dothat65.mindfulMomentsbackend.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodTrendService {
    private final JournalRepository journalRepository = null;

    @Autowired
    public MoodTrendService() {
    }

    public List<String> getMoodTrend(Long userId) {
        List<Journal> journals = journalRepository.findByUserIdOrderByDateDesc(userId);
        return journals.stream().map(Journal::getMood).collect(Collectors.toList());
    }
}
