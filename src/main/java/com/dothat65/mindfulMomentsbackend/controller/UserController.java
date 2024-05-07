package com.dothat65.mindfulMomentsbackend.controller;
import com.dothat65.mindfulMomentsbackend.model.User;
import com.dothat65.mindfulMomentsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing user-related operations.
 */
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
     * @return ResponseEntity with the created user and HTTP status 200 (OK).
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Retrieves a user by username.
     * @param username The username of the user to retrieve.
     * @return ResponseEntity with the retrieved user and HTTP status 200 (OK).
     */
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    /**
     * Updates an existing user.
     * @param user The updated user object.
     * @return ResponseEntity with the updated user and HTTP status 200 (OK).
     */
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Deletes a user by username.
     * @param username The username of the user to delete.
     * @return ResponseEntity with HTTP status 200 (OK) upon successful deletion.
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }

    /**
     * Resets the password for a user.
     * @param username The username of the user whose password is to be reset.
     * @param newPassword The new password for the user.
     * @return ResponseEntity with the updated user and HTTP status 200 (OK).
     */
    @PutMapping("/{username}/reset-password")
    public ResponseEntity<User> resetPassword(@PathVariable String username, @RequestBody String newPassword) {
        User user = userService.resetPassword(username, newPassword);
        return ResponseEntity.ok(user);
    }
}
