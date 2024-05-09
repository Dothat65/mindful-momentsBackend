package com.dothat65.mindfulMomentsbackend.service;
import com.dothat65.mindfulMomentsbackend.model.User;
import com.dothat65.mindfulMomentsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;


/**
 * Service class for handling business logic related to User entities.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructor for UserService.
     *
     * @param userRepository the UserRepository to be used by this service
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Creates a new User entity.
     *
     * @param user the User entity to be created
     * @return ResponseEntity with the created User entity and appropriate HTTP status
     */
    public ResponseEntity<?> createUser(User user) {
        User existingUserByUsername = userRepository.findByUsername(user.getUsername());
        User existingUserByEmail = userRepository.findByEmail(user.getEmail());

        if (existingUserByUsername != null) {
            return new ResponseEntity<>("User already exists with username: " + user.getUsername(), HttpStatus.BAD_REQUEST);
        }

        if (existingUserByEmail != null) {
            return new ResponseEntity<>("User already exists with email: " + user.getEmail(), HttpStatus.BAD_REQUEST);
        }

        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /**
     * Retrieves a User entity by username.
     *
     * @param username the username of the User entity to be retrieved
     * @return ResponseEntity with the retrieved User entity and appropriate HTTP status
     */
    public ResponseEntity<?> getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("User not found with username: " + username, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Retrieves a User entity by email.
     *
     * @param email the email of the User entity to be retrieved
     * @return ResponseEntity with the retrieved User entity and appropriate HTTP status
     */
    public ResponseEntity<?> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Deletes a User entity by username.
     *
     * @param username the username of the User entity to be deleted
     * @return ResponseEntity with appropriate HTTP status
     */
    public ResponseEntity<?> deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("User not found with username: " + username, HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Updates a User entity.
     *
     * @param user the User entity to be updated
     * @return ResponseEntity with the updated User entity and appropriate HTTP status
     */
    public ResponseEntity<?> updateUser(User user) {
        User updatedUser = userRepository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * Resets the password of a User entity.
     *
     * @param username the username of the User entity whose password is to be reset
     * @param newPassword the new password to be set
     * @return ResponseEntity with the updated User entity and appropriate HTTP status
     */
    public ResponseEntity<?> resetPassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        user.setPassword(newPassword);
        User updatedUser = userRepository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * Logs in a user.
     *
     * @param username the username of the user to log in
     * @param password the password of the user to log in
     * @return ResponseEntity with the User entity that was logged in and appropriate HTTP status
     */
    public ResponseEntity<?> login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        if (!user.getPassword().equals(password)) {
            return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
        }

        // Generate a JWT for the user
        String token = Jwts.builder()
                .setSubject(user.getId().toString())  // Set the subject to the user ID
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // Set token to expire in 1 hour
                .signWith(SignatureAlgorithm.HS256, "0x7A1B3CF9E2D847A10FEDCBA9876543210ABCDEF0123456789ABCDEFEDCBA987")  // Sign the token with a secret key
                .compact();

        // Return the token in the response
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    }