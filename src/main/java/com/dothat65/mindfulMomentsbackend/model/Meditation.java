package com.dothat65.mindfulMomentsbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "meditations")
public class Meditation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int duration; // in minutes
    private String audioLink; // URL to the audio file

    public Meditation(String name, String description, int duration, String audioLink) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.audioLink = audioLink;
    }

    public Meditation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }
}