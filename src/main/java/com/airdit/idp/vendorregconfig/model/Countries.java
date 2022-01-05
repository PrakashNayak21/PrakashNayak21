package com.airdit.idp.vendorregconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import com.airdit.idp.vendorregconfig.configuration.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdOn" }, allowGetters = true)
@Validated
@Table(name = "countries")
public class Countries {
	@Id
	@Column(name = "countriesid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countries_seq")
	@GenericGenerator(name = "countries_seq", strategy = "com.airdit.idp.vendorregconfig.configuration.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "COUN"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String countriesid;

	@Column(name = "Countries_Name")
	private String name;

	@Column(name = "Flag_Name")
	private String flag;

	@Column(name = "Countries_code")
	private String code;

	@Column(name = "isdelete")
	private boolean isDelete;

	public Countries() {
		super();
	}

	public String getCountriesid() {
		return countriesid;
	}

	public void setCountriesid(String countriesid) {
		this.countriesid = countriesid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}