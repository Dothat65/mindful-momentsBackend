package com.dothat65.mindfulMomentsbackend.service;

import com.dothat65.mindfulMomentsbackend.model.User;
import com.dothat65.mindfulMomentsbackend.model.Journal;
import com.dothat65.mindfulMomentsbackend.repository.JournalRepository;
import com.dothat65.mindfulMomentsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * Service class for handling business logic related to Journal entities.
 */
@Service
public class JournalService {



    private final JournalRepository journalRepository;
    private final UserRepository userRepository;

    /**
     * Constructor for JournalService.
     *
     * @param journalRepository the JournalRepository to be used by this service
     */
    @Autowired
    public JournalService(JournalRepository journalRepository, UserRepository userRepository) {
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new Journal entity.
     * @param journal the Journal entity to be created
     * @return the created Journal entity
     */

    public Journal createJournal(Journal journal, Long username){
        User user = userRepository.findById(username).orElseThrow(() -> new DataAccessException("User not found") {});
        System.out.println("Retrieved user: " + user);  // Log user object

        journal.setUser(user);

        System.out.println("Saving journal: " + journal);  // Log journal object before saving
        Journal savedJournal = journalRepository.save(journal);
        System.out.println("Saved journal: " + savedJournal);  // Log journal object after saving

        return savedJournal;
    }


    /**
     * Deletes a Journal entity by id.
     * @param id the id of the Journal entity to be deleted
     * @return ResponseEntity with appropriate HTTP status
     */

    public void deleteJournal(Long id, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EmptyResultDataAccessException("User not found", 1));
        Journal journal = journalRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Journal not found", 1));

        if (!journal.getUser().equals(user)) {
            throw new IllegalArgumentException("User does not have permission to delete this journal");
        }

        journalRepository.delete(journal);
    }


}