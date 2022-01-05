package com.airdit.idp.vendorregconfig.configuration;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;


@Configuration
public class EmailConfig {
@Autowired
private Environment env;
@Bean
public SimpleMailMessage emailTemplate()
{
SimpleMailMessage message = new SimpleMailMessage();
message.setTo("user@gmail.com");
message.setFrom("admin@gmail.com");
message.setSubject("Important email");
message.setText("FATAL - Application crash. Save your job !!");
return message;
}


@Bean
public JavaMailSender getMailSender() {
JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

mailSender.setHost(env.getProperty("spring.mail.host"));
mailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
mailSender.setUsername(env.getProperty("spring.mail.username"));
mailSender.setPassword(env.getProperty("spring.mail.password"));

Properties javaMailProperties = new Properties();
javaMailProperties.put("mail.smtp.starttls.enable", "true");
javaMailProperties.put("mail.smtp.auth", "true");
javaMailProperties.put("mail.transport.protocol", "smtp");
javaMailProperties.put("mail.debug", "true");

mailSender.setJavaMailProperties(javaMailProperties);
return mailSender;
}
@Bean
public FreeMarkerViewResolver freemarkerViewResolver() {
FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
resolver.setCache(true);
resolver.setPrefix("");
resolver.setSuffix(".ftl");
return resolver;
}

@Bean
public FreeMarkerConfigurer freemarkerConfig() {
FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/ftl/");
return freeMarkerConfigurer;
}
}
