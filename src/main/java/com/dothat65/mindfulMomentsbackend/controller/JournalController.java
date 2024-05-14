package com.dothat65.mindfulMomentsbackend.controller;
import com.dothat65.mindfulMomentsbackend.model.Journal;
import com.dothat65.mindfulMomentsbackend.service.JournalService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller class for handling HTTP requests related to Journal entities.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/journal")
public class JournalController {
    private final JournalService journalService;


    /**
     * Constructor for JournalController.
     *
     * @param journalService the JournalService to be used by this controller
     */
    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    /**
     * Creates a new Journal entity.
     *
     * @param journal the Journal entity to be created
     * @return the created Journal entity
     */

    @PostMapping
    public Journal createJournal(@RequestBody Journal journal, @RequestHeader("Authorization") String token) {
        System.out.println("Received token: " + token);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring("Bearer ".length());
        }

        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey("0x7A1B3CF9E2D847A10FEDCBA9876543210ABCDEF0123456789ABCDEFEDCBA987")
                .build()
                .parseClaimsJws(token);

        Long userId = Long.parseLong(jws.getBody().getSubject());
        System.out.println("Parsed userId: " + userId);  // Log userId

        System.out.println("Received journal: " + journal);  // Log journal object

        Journal createdJournal = journalService.createJournal(journal, userId);
        System.out.println("Created journal: " + createdJournal);  // Log created journal object

        return createdJournal;
    }



    /**
     * Deletes a Journal entity by id.
     * @param id the id of the Journal entity to be deleted
     *  @return ResponseEntity with appropriate HTTP status
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Jws<Claims> jws = Jwts.parserBuilder()  // (1)
                .setSigningKey("0x7A1B3CF9E2D847A10FEDCBA9876543210ABCDEF0123456789ABCDEFEDCBA987")         // (2)
                .build()                    // (3)
                .parseClaimsJws(token); // (4)

        Long userId = Long.parseLong(jws.getBody().getSubject()); // extract user ID from token
        journalService.deleteJournal(id, userId);
        return ResponseEntity.ok().build();
    }
}
