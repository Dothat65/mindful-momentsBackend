package com.dothat65.mindfulMomentsbackend.repository;

import com.dothat65.mindfulMomentsbackend.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dothat65.mindfulMomentsbackend.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long>{
    /**
     * Finds all journal entries associated with a user.
     * @param user The user whose journal entries are to be found.
     * @return A list of journal entries associated with the user.
     */
    List<Journal> findByUser(User user);
}
