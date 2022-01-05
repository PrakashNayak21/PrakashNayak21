package com.airdit.idp.vendorregconfig.Exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
