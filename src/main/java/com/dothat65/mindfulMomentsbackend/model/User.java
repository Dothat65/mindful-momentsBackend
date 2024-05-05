package com.dothat65.mindfulMomentsbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



/**
 * This class is a model for the User object.
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** The username of the user. */
    private String username;
    /** The password of the user. */
    private String passwords;
    /** The email of the user. */
    private String email;
    /** The first name of the user. */
    private String firstName;
    /** The last name of the user. */
    private String lastName;

    /**
     * Constructor for the User object.
     * @param username The username of the user.
     * @param passwords The password of the user.
     * @param email The email of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     */
    public User(String username, String passwords, String email, String firstName, String lastName) {
        this.username = username;
        this.passwords = passwords;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    /**
     * Getter for the username.
     * @return The username of the user.
     */

    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username.
     * @param username The username of the user.
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the password.
     * @return The password of the user.
     */

    public String getPasswords() {
        return passwords;
    }

    /**
     * Setter for the password.
     * @param passwords The password of the user.
     */

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    /**
     * Getter for the email.
     * @return The email of the user.
     */

    public String getEmail() {
        return email;
    }

    /**
     * Setter for the email.
     * @param email The email of the user.
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for the first name.
     * @return The first name of the user.
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the first name.
     * @param firstName The first name of the user.
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the last name.
     * @return The last name of the user.
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the last name.
     * @param lastName The last name of the user.
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




}
