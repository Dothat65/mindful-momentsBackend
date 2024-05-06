package com.dothat65.mindfulMomentsbackend.service;

import com.dothat65.mindfulMomentsbackend.model.User;
import com.dothat65.mindfulMomentsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {
    User authenticateUser(String username, String password);
    boolean resetPassword(String email);
    boolean changePassword(String username, String oldPassword, String newPassword);
}

