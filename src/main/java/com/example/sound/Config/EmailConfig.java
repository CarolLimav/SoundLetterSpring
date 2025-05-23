package com.example.sound.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class EmailConfig {
	 @Bean
	    public TemplateEngine emailTemplateEngine() {
	        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        templateEngine.setTemplateResolver(emailTemplateResolver());
	        return templateEngine;
	    }

	    private ClassLoaderTemplateResolver emailTemplateResolver() {
	        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	        templateResolver.setPrefix("/templates/");
	        templateResolver.setSuffix(".html");
	        templateResolver.setTemplateMode(TemplateMode.HTML);
	        templateResolver.setCharacterEncoding("UTF-8");
	        templateResolver.setCacheable(false);
	        return templateResolver;
	    }
}
