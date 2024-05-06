package com.dothat65.mindfulMomentsbackend.service;

import com.dothat65.mindfulMomentsbackend.model.User;
import com.dothat65.mindfulMomentsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists with username: " + user.getUsername());
        }
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        return user;
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        userRepository.delete(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Add more methods as needed
}


