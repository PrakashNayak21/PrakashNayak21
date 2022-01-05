package com.airdit.idp.vendorregconfig.model;

import java.util.List;

public class Mail {

private String mailFrom;

private String mailTo;

private String mailCc;

private String mailBcc;

private String mailSubject;

private String mailContent;

private String contentType;

private List < Object > attachments;

public Mail() {
contentType = "text/plain";
}
/*
public Mail() {
}



public Mail(String recipient, String subject, String message,String fileToAttach) {
this.recipient = recipient;
this.subject = subject;
this.message = message;
this.fileToAttach=fileToAttach;
}
private String recipient;
private String subject;
private String message;
private String fileToAttach;
public String getRecipient() {
return recipient;
}



public void setRecipient(String recipient) {
this.recipient = recipient;
}



public String getSubject() {
return subject;
}



public void setSubject(String subject) {
this.subject = subject;
}



public String getMessage() {
return message;
}



public void setMessage(String message) {
this.message = message;
}



public String getFileToAttach() {
return fileToAttach;
}



public void setFileToAttach(String fileToAttach) {
this.fileToAttach = fileToAttach;
}
*/



public String getMailFrom() {
return mailFrom;
}



public void setMailFrom(String mailFrom) {
this.mailFrom = mailFrom;
}



public String getMailTo() {
return mailTo;
}



public void setMailTo(String mailTo) {
this.mailTo = mailTo;
}



public String getMailCc() {
return mailCc;
}



public void setMailCc(String mailCc) {
this.mailCc = mailCc;
}



public String getMailBcc() {
return mailBcc;
}



public void setMailBcc(String mailBcc) {
this.mailBcc = mailBcc;
}



public String getMailSubject() {
return mailSubject;
}



public void setMailSubject(String mailSubject) {
this.mailSubject = mailSubject;
}



public String getMailContent() {
return mailContent;
}



public void setMailContent(String mailContent) {
this.mailContent = mailContent;
}



public String getContentType() {
return contentType;
}



public void setContentType(String contentType) {
this.contentType = contentType;
}



public List<Object> getAttachments() {
return attachments;
}



public void setAttachments(List<Object> attachments) {
this.attachments = attachments;
}

}
