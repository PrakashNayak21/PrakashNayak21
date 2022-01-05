package com.airdit.idp.vendorregconfig.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.RecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.ResourceNotFoundException;
import com.airdit.idp.vendorregconfig.model.LegalStatus;

public interface LegalStatusService {
	LegalStatus insert(LegalStatus legalStatus) throws ResourceNotFoundException;
	
	List<LegalStatus> getLegalStatus() throws AttchmentsRecordNotFoundException;
	
	LegalStatus getStatusCode(String statusCode) throws RecordNotFoundException;
	
	LegalStatus updateLegalStatus(LegalStatus legalStatus) throws ResourceNotFoundException, RecordNotFoundException;
	
	void deleteLegalStatus(String statusCode ) throws RecordNotFoundException;
	
	String deleteLegalStatusPartially(String attachmentid) throws NoSuchElementException, AttchmentsRecordNotFoundException;

}

