package com.airdit.idp.vendorregconfig.comppackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airdit.idp.vendorregconfig.model.Attachments;
import com.google.gson.Gson;

@Component
//public class StringToDescriptionConverter implements Converter<String, AttachmentModels> {
public class StringToDescriptionConverter implements Converter<String, Attachments> {

	@Autowired
	private Gson gson;

//	@Override
//	public AttachmentModels convert(String source) {
//		return gson.fromJson(source, AttachmentModels.class);
//	}

	@Override
	public Attachments convert(String source) {
		return gson.fromJson(source, Attachments.class);
	}
}