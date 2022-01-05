package com.airdit.idp.vendorregconfig.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import com.airdit.idp.vendorregconfig.configuration.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdOn" }, allowGetters = true)
@Validated
@Table(name = "attachments_details")
public class Attachments {
	@Id
	@Column(name = "attachmentid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment_seq")
	@GenericGenerator(name = "attachment_seq", strategy = "com.airdit.idp.vendorregconfig.configuration.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ATCH"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String attachmentid;

	@NotNull(message = "{attachments.description.NotNull}")
	@NotBlank(message = "{attachments.description.NotBlank}")
	@NotEmpty(message = "{attachments.description.NotEmpty}")
	@Column(name = "description")
	private String description;

	/*
	 * @NotBlank(message = "attachments.mandatory.NotBlank")
	 * 
	 * @NotEmpty(message = "attachments.mandatory.NotEmpty")
	 * 
	 * @NotNull(message = "attachments.mandatory.NotNull")
	 */
	@Column(name = "mandatory")
	private boolean mandatory;

	/*
	 * @NotBlank(message = "attachments.docName.NotBlank")
	 * 
	 * @NotNull(message = "attachments.docName.NotNull")
	 * 
	 * @NotEmpty(message = "attachments.docName.NotEmpty")
	 * 
	 * @NotNull(message = "docName may not be null")
	 */
	@Column(name = "docName")
	private String docName;

	@Column(name = "docType")
	private String docType;

	@Column(name = "isdelete")
	private boolean isDelete;

	@Column(name = "Createdon", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdOn;

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getAttachmentid() {
		return attachmentid;
	}

	public void setAttachmentid(String attachmentid) {
		this.attachmentid = attachmentid;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Attachments() {
		super();

	}

	public Attachments(String description, String attachmentid) {
		super();
		this.description = description;
		this.attachmentid = attachmentid;
	}

	public Attachments(String description) {
		super();
		this.description = description;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getAttachmentId() {
		return attachmentid;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentid = attachmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	@Override
	public String toString() {
		return "Attachments [attachmentid=" + attachmentid + ", description=" + description + ", mandatory=" + mandatory
				+ ", docName=" + docName + ", docType=" + docType + "]";
	}
}
