package com.dothat65.mindfulMomentsbackend.service;

import com.dothat65.mindfulMomentsbackend.model.User;
import com.dothat65.mindfulMomentsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// enter business logic into the UserServiceImpl class to implement the methods defined in the UserService interface.
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticateUser(String username, String password) {
        // Implement authentication logic here
        return null;
    }

    @Override
    public boolean resetPassword(String email) {
        // Implement password reset logic here
        return false;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        // Implement password change logic here
        return false;
    }
}
