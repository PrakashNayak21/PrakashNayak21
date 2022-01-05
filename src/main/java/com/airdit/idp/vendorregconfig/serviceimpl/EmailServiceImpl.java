package com.airdit.idp.vendorregconfig.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.airdit.idp.vendorregconfig.model.Mail;
import com.airdit.idp.vendorregconfig.service.EmailService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SimpleMailMessage preConfiguredMessage;

	@Autowired
	private Configuration freemarkerConfig;

	@Override
	public void sendSimpleMessage(Mail mail) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException, MessagingException {
//String to, String subject, String body
		/*
		 * MimeMessage mimeMessage = mailSender.createMimeMessage(); try {
		 * MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
		 * true); mimeMessageHelper.setSubject(mail.getMailSubject());
		 * mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(),
		 * "technicalkeeda.com")); mimeMessageHelper.setTo(mail.getMailTo());
		 * mimeMessageHelper.setText(mail.getMailContent());
		 * mailSender.send(mimeMessageHelper.getMimeMessage()); } catch
		 * (MessagingException e) { e.printStackTrace(); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); } SimpleMailMessage
		 * message = new SimpleMailMessage(); message.setTo(mail.getRecipient());
		 * message.setSubject(mail.getSubject()); message.setText(mail.getMessage());
		 * mailSender.send(message);
		 */
		System.out.print("Email service1");
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		System.out.println("Email service2");
		Map<String, Object> model = new HashMap();
		model.put("user", "Prakash");
		System.out.println("Email service3");
// Map<String, Object> model = new HashMap();
// model.put("user", "qpt");
// String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "welcome.vm", model);
// set loading location to src/main/resources
// You may want to use a subfolder such as /templates here
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
		System.out.println("Email service4");
		Template t = freemarkerConfig.getTemplate("welcome.ftl");
		System.out.println("Email service5");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		System.out.println("Email service6");
		helper.setFrom(new InternetAddress(mail.getMailFrom(), "airditsoftware.com"));
		helper.setTo(mail.getMailTo());
//"set-your-recipient-email-here@gmail.com"
//technicalkeeda.com
		helper.setText(text, true); // set to html
		helper.setSubject(mail.getMailSubject());
//"Hi"
		System.out.print("Email service7");
		mailSender.send(message);
		System.out.print("Email service8");
	}

	@Override
	public void sendMailWithAttachment(Mail mail) throws MessagingException, IOException {
//String to, String subject, String body, String fileToAttach
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getMailTo()));
				mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
				mimeMessage.setSubject(mail.getMailSubject());
				mimeMessage.setText(mail.getMailContent());
				FileSystemResource file = new FileSystemResource(new File(mail.getAttachments().get(0).toString()));
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.addAttachment("logo.jpg", file);
			}
		};
		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void sendMailWithInlineResources(Mail mail) throws SendFailedException {
//String to, String subject, String fileToAttach
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getMailTo()));
				mimeMessage.setFrom(new InternetAddress(mail.getMailFrom()));
				mimeMessage.setSubject(mail.getMailSubject());
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);
// ClassPathResource file = new ClassPathResource("cat.jpg");
//helper.addInline("id101", file);
				FileSystemResource res = new FileSystemResource(new File(mail.getMailContent()));
				helper.addInline("identifier1234", res);
			}
		};
		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}

	/**
	 * This method will send a pre-configured message
	 */
	@Override
	public void sendPreConfiguredMail(Mail mail) {
//String message
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setText(mail.getMailContent());
		mailSender.send(mailMessage);
	}
}
