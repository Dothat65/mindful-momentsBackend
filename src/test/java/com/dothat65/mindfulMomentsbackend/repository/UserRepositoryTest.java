package com.dothat65.mindfulMomentsbackend.repository;
import com.dothat65.mindfulMomentsbackend.model.User;
import com.dothat65.mindfulMomentsbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User("testUsername", "testPassword", "testEmail@example.com", "Test", "User");
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("testUsername");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testUsername");
    }

    // Add more tests for other repository methods
}