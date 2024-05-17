package com.dothat65.mindfulMomentsbackend.controller;

import com.dothat65.mindfulMomentsbackend.service.MoodTrendService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mood-trend")
public class MoodTrendController {

    @Autowired
    public MoodTrendController(MoodTrendService moodTrendService) {
    }

    // In MoodTrendController.java
@GetMapping
public List<String> getMoodTrend(@RequestHeader("Authorization") String token) {
    Jws<Claims> jws = Jwts.parserBuilder()
            .setSigningKey("0x7A1B3CF9E2D847A10FEDCBA9876543210ABCDEF0123456789ABCDEFEDCBA987") // need to change this key
            .build()
            .parseClaimsJws(token);
    Long userId = Long.parseLong(jws.getBody().getSubject());
    MoodTrendService moodTrendService = new MoodTrendService();
    return moodTrendService.getMoodTrend(userId);
}
}
