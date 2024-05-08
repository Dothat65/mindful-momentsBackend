package com.dothat65.mindfulMomentsbackend.repository;

import com.dothat65.mindfulMomentsbackend.model.Meditation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeditationRepository extends JpaRepository<Meditation, Long> {
}