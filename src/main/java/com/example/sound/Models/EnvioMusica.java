package com.example.sound.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnvioMusica {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeRemetente;
    private String emailRemetente;
    private String emailDestinatario;
    private String mensagemPessoal;

    private String nomeMusica;
    private String artista;
    private String linkSpotify;

    private LocalDateTime dataEnvio;
}
