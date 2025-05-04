package com.example.sound.Dtos;

public record EnvioMusicaDto(
		 String nomeRemetente,
		    String emailRemetente,
		    String emailDestinatario,
		    String mensagemPessoal,
		    String nomeMusica,
		    String artista,
		    String linkSpotify
		   ) {}
