package com.airdit.idp.vendorregconfig.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airdit.idp.vendorregconfig.model.Mail;
import com.airdit.idp.vendorregconfig.service.EmailService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;

@RestController
@RequestMapping("/api/v1/mail/")
public class EmailController {
	@Autowired
	private EmailService emailService;

	@PostMapping("/sendtextemail")
	public ResponseEntity<String> sendEmail(@RequestBody Mail mail) throws MailException, VelocityException,
			MalformedTemplateNameException, ParseException, MessagingException, IOException, TemplateException {
		System.out.print("Email controller");
		emailService.sendSimpleMessage(mail);
		return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
	}

@PostMapping("/sendattachmentemail")
public ResponseEntity<String> sendAttachmentEmail(@RequestBody Mail mail) throws MessagingException {
//emailService
return new ResponseEntity<>("Attachment mail sent successfully", HttpStatus.OK);
}
}