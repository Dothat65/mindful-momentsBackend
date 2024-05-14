package com.dothat65.mindfulMomentsbackend.controller;
import com.dothat65.mindfulMomentsbackend.model.User;
import com.dothat65.mindfulMomentsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * Controller class for managing user-related operations.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     * @param user The user object to be created.
     * @return ResponseEntity with the created user and appropriate HTTP status.
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Retrieves a user by username.
     * @param username The username of the user to retrieve.
     * @return ResponseEntity with the retrieved user and appropriate HTTP status.
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    /**
     * Updates an existing user.
     * @param user The updated user object.
     * @return ResponseEntity with the updated user and appropriate HTTP status.
     */
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * Deletes a user by username.
     * @param username The username of the user to delete.
     * @return ResponseEntity with appropriate HTTP status.
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }

    /**
     * Resets the password for a user.
     * @param username The username of the user whose password is to be reset.
     * @return ResponseEntity with the updated user and appropriate HTTP status.
     */
    @PutMapping("/{username}/reset-password")
    public ResponseEntity<?> resetPassword(@PathVariable String username, @RequestBody Map<String, String> passwordMap) {
        return userService.resetPassword(username, passwordMap.get("newPassword"));
    }

    /**
     * Logs in a user.
     * @param user The user object to be logged in.
     * @return ResponseEntity with the logged-in user and appropriate HTTP status.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        ResponseEntity<?> response = userService.login(user.getUsername(), user.getPassword());

        if (response.getStatusCode() == HttpStatus.OK) {
            String token = (String) response.getBody();
            return new ResponseEntity<>(Collections.singletonMap("token", token), HttpStatus.OK);
        } else {
            return response;
        }
    }
}
