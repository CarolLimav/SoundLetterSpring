package com.example.sound.Models;


public class SpotifyTrack {
	 private String nome;
	 private String artista;
	 private String link;

	 public SpotifyTrack(String nome, String artista, String link) {
	     this.nome = nome;
	     this.artista = artista;
	     this.link = link;
	 }
	 
	 public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getArtista() {
	        return artista;
	    }

	    public void setArtista(String artista) {
	        this.artista = artista;
	    }

	    public String getLink() {
	        return link;
	    }

	    public void setLink(String link) {
	        this.link = link;
	    }
}
