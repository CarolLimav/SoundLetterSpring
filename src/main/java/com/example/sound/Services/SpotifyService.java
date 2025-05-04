package com.example.sound.Services;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import com.example.sound.Config.SpotifyConfig;
import com.example.sound.Models.SpotifyTrack;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SpotifyService {
	@Autowired
    private SpotifyConfig config;

    private final RestTemplate restTemplate = new RestTemplate();

    private String obterAccessToken() {
        String clientId = config.getClientId();
        String clientSecret = config.getClientSecret();
        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedAuth);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                "https://accounts.spotify.com/api/token",
                HttpMethod.POST,
                request,
                JsonNode.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().get("access_token").asText();
        } else {
            throw new RuntimeException("Falha ao obter token do Spotify");
        }
    }

    public List<SpotifyTrack> buscarMusicas(String query) {
        String token = obterAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = "https://api.spotify.com/v1/search?q=" + UriUtils.encode(query, StandardCharsets.UTF_8) + "&type=track&limit=5";

        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
        List<SpotifyTrack> tracks = new ArrayList<>();

        JsonNode items = response.getBody().get("tracks").get("items");
        for (JsonNode trackNode : items) {
            String nome = trackNode.get("name").asText();
            String artista = trackNode.get("artists").get(0).get("name").asText();
            String link = trackNode.get("external_urls").get("spotify").asText();
            tracks.add(new SpotifyTrack(nome, artista, link));
        }

        return tracks;
    }
}
	    
