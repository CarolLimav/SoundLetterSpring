package com.example.sound.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;

import com.example.sound.Dtos.EnvioMusicaDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	 @Autowired
	    private JavaMailSender mailSender;
	    
	    @Autowired
	    private TemplateEngine templateEngine; // Injeção do TemplateEngine

	    public void enviarMusicaPorEmail(EnvioMusicaDto dto) throws MessagingException {
	        // Prepara o contexto Thymeleaf
	        Context context = new Context();
	        context.setVariable("nomeRemetente", dto.nomeRemetente());
	        context.setVariable("nomeMusica", dto.nomeMusica());
	        context.setVariable("artista", dto.artista());
	        context.setVariable("linkSpotify", dto.linkSpotify());
	        context.setVariable("mensagemPessoal", dto.mensagemPessoal());
	        
	        // Processa o template HTML
	        String htmlContent = templateEngine.process("email-musica", context);
	        
	        // Cria e configura a mensagem
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
	        
	        helper.setTo(dto.emailDestinatario());
	        helper.setSubject(dto.nomeRemetente() + " te enviou uma música especial!");
	        helper.setText(htmlContent, true); // true = HTML
	        
	        mailSender.send(message);
	    }
}
