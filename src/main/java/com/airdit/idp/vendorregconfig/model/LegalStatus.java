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
/*import javax.validation.constraints.NotNull;*/

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.airdit.idp.vendorregconfig.configuration.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdOn"}, allowGetters = true)
@Table(name="legalstatus")
public class LegalStatus {
	@Id
	@Column(name = "statusCode")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "legalstatus_seq")
	@GenericGenerator(name = "legalstatus_seq", strategy = "com.airdit.idp.vendorregconfig.configuration.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "LGST"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	
	private String statusCode;

	@NotNull(message = "{attachments.description.NotNull}")
	@NotBlank(message = "{attachments.description.NotBlank}")
	@NotEmpty(message = "{attachments.description.NotEmpty}") 
	@Column(name="description")
	private String description ;
	
	@Column(name="isdelete")
	private boolean isDelete;
	

	@Column(name="Createdon",nullable =false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdOn;
	
	

	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "LegalStatus [StatusCode=" + statusCode + ", Description=" + description + "]";
	}
	public LegalStatus() {
		super();
		
	}
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
