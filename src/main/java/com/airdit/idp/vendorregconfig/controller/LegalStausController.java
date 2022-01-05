package com.airdit.idp.vendorregconfig.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.RecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.ResourceNotFoundException;
import com.airdit.idp.vendorregconfig.model.LegalStatus;
import com.airdit.idp.vendorregconfig.service.LegalStatusService;

@RestController
@RequestMapping("/legalstatus")
public class LegalStausController {
	@Autowired
	private LegalStatusService LegalStatusService; 
	
	@GetMapping("/legalstatuslist")
	public List<LegalStatus> getLegalstatus() throws AttchmentsRecordNotFoundException{
		return this.LegalStatusService.getLegalStatus();
		
	}
	@GetMapping("/legalstatus/{statusCode}") 
	public LegalStatus getStatusCode(@PathVariable("statusCode") @NotBlank @Size(max = 10) String statusCode) throws RecordNotFoundException {
		return this.LegalStatusService.getStatusCode(statusCode);
		
	}
	@PostMapping("/addlegalstatus")
	public LegalStatus insertLegalStatus(@Valid @RequestBody LegalStatus legalStatus) throws ResourceNotFoundException {
		return this.LegalStatusService.insert(legalStatus);
		
	}
	
	@PutMapping("/legalstatus")
	public LegalStatus updateLegalStatus( @Valid @RequestBody LegalStatus legalStatus) throws ResourceNotFoundException, RecordNotFoundException {
		return this.LegalStatusService.updateLegalStatus( legalStatus);
		
	}
	@DeleteMapping("/deletelegalstatus/{statusCode}")
	public void deleteLegalStatus(@PathVariable("statusCode") String statusCode) throws RecordNotFoundException {
		this.LegalStatusService.deleteLegalStatus(statusCode);
		
	}
	
	@DeleteMapping("/deleteLegalStatusPartially/{statusCode}")
	public String deleteLegalStatusPartially(@PathVariable("statusCode") String statusCode) throws RecordNotFoundException,NoSuchElementException, AttchmentsRecordNotFoundException {
		return this.LegalStatusService.deleteLegalStatusPartially(statusCode);
		
	}
	
}
