package com.airdit.idp.vendorregconfig.Exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends Exception {
	public ResourceNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
