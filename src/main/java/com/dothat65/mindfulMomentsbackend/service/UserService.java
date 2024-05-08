package com.dothat65.mindfulMomentsbackend.service;
import com.dothat65.mindfulMomentsbackend.model.User;
import com.dothat65.mindfulMomentsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Service class for handling business logic related to User entities.
 */
@Service
public abstract class UserService {

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
     * @return the created User entity
     * @throws IllegalArgumentException if a user with the same username or email already exists
     */
    public User createUser(User user) {
        User existingUserByUsername = userRepository.findByUsername(user.getUsername());
        User existingUserByEmail = userRepository.findByEmail(user.getEmail());

        if (existingUserByUsername != null) {
            throw new IllegalArgumentException("User already exists with username: " + user.getUsername());
        }

        if (existingUserByEmail != null) {
            throw new IllegalArgumentException("User already exists with email: " + user.getEmail());
        }

        return userRepository.save(user);
    }

    /**
     * Retrieves a User entity by username.
     *
     * @param username the username of the User entity to be retrieved
     * @return the retrieved User entity
     * @throws IllegalArgumentException if no user with the given username exists
     */
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        return user;
    }

    /**
     * Retrieves a User entity by email.
     *
     * @param email the email of the User entity to be retrieved
     * @return the retrieved User entity
     * @throws IllegalArgumentException if no user with the given email exists
     */
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        return user;
    }

    /**
     * Deletes a User entity by username.
     *
     * @param username the username of the User entity to be deleted
     * @throws IllegalArgumentException if no user with the given username exists
     */
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        userRepository.delete(user);
    }

    /**
     * Updates a User entity.
     *
     * @param user the User entity to be updated
     * @return the updated User entity
     */
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Resets the password of a User entity.
     *
     * @param username the username of the User entity whose password is to be reset
     * @param newPassword the new password to be set
     * @return the updated User entity
     */

        public User resetPassword(String username, String newPassword) {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }
            user.setPassword(newPassword);
            userRepository.save(user);
            return user; // return the updated user
        }

    public abstract User authenticateUser(String username, String password);

    public abstract boolean resetPassword(String email);

    public abstract boolean changePassword(String username, String oldPassword, String newPassword);
}