package com.example.sound.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sound.Models.SpotifyTrack;
import com.example.sound.Services.SpotifyService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://681827d8128f2b4f82a22c60--soundletter.netlify.app/")  
public class SpotifyController {
	@Autowired
    private SpotifyService spotifyService;

	@GetMapping("/search")
    public ResponseEntity<List<SpotifyTrack>> buscarMusicas(@RequestParam String query) {
        return ResponseEntity.ok(spotifyService.buscarMusicas(query));
    }
}
