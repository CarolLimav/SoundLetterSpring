package com.example.sound.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sound.Dtos.EnvioMusicaDto;
import com.example.sound.Models.ApiResponse;
import com.example.sound.Models.SpotifyTrack;
import com.example.sound.Services.EmailService;
import com.example.sound.Services.SpotifyService;

@RestController
@RequestMapping("/api/musica")
@CrossOrigin(origins = "https://soundletter.netlify.app/")  
public class EnvioMusicaController {
	 @Autowired
	 private EmailService emailService;

	 
	 public EnvioMusicaController(EmailService emailService) {
	      this.emailService = emailService;
	  }
	 
	 @PostMapping("/enviar")  
	 public ResponseEntity<ApiResponse> enviarMusica(@RequestBody EnvioMusicaDto dto) {
	        try {
	            emailService.enviarMusicaPorEmail(dto);
	            return ResponseEntity.ok(
	                new ApiResponse(
	                    "success",
	                    "E-mail enviado com sucesso!",
	                    null
	                )
	            );
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(
	                new ApiResponse(
	                    "error",
	                    "Falha ao enviar e-mail: " + e.getMessage(),
	                    e.getClass().getSimpleName()
	                )
	            );
	        }
	    }
	 
}
