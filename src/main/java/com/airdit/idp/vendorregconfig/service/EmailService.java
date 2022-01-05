package com.airdit.idp.vendorregconfig.service;

import java.io.IOException;
import javax.mail.MessagingException;
import org.apache.velocity.exception.VelocityException;
import org.springframework.mail.MailException;
import com.airdit.idp.vendorregconfig.model.Mail;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;



public interface EmailService {
//String to, String subject, String text
void sendSimpleMessage(Mail mail)throws MessagingException,MailException, VelocityException, MalformedTemplateNameException, ParseException, IOException, TemplateException;
void sendMailWithAttachment(Mail mail)throws MessagingException, MailException,IOException;
//String to, String subject, String body, String fileToAttach
void sendMailWithInlineResources(Mail mail)throws MessagingException,MailException;
//String to, String subject, String fileToAttach
void sendPreConfiguredMail(Mail mail)throws MessagingException,MailException;
//String message
}