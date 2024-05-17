package com.dothat65.mindfulMomentsbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Represents a journal entry in the application.
 * Each journal entry is associated with a user and has content and a date.
 */
@Entity
public class Journal {

    /**
     * The unique identifier of the journal entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who created the journal entry.
     * This is a many-to-one relationship, as one user can create many journal entries.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    /**
     * The content of the journal entry.
     * This can be up to 1000 characters long.
     */
    @Column (length = 1000)
    private String content;
    private String Mood;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {return Mood;}

    public void setMood(String mood) {
        this.Mood = mood;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", content='" + content + '\''+
                ", user=" + (user != null ? user.getId() : "null") +
                '}';
    }
}



