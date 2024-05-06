package com.dothat65.mindfulMomentsbackend.repository;

import com.dothat65.mindfulMomentsbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository is an interface that extends JpaRepository to provide CRUD operations for User entities.
 * Spring Data JPA will automatically implement this interface at runtime.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their username.
     *
     * @param username the username of the user to find
     * @return the User entity with the given username, or null if no such user exists
     */
    User findByUsername(String username);

    /**
     * Find a user by their email.
     *
     * @param email the email of the user to find
     * @return the User entity with the given email, or null if no such user exists
     */
    User findByEmail(String email);
}