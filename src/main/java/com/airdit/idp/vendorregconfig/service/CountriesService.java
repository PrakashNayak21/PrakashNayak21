package com.airdit.idp.vendorregconfig.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.RecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.ResourceNotFoundException;
import com.airdit.idp.vendorregconfig.model.Countries;

public interface CountriesService {
	Countries insert(Countries countries) throws ResourceNotFoundException;
	
	List<Countries> getCountries() throws AttchmentsRecordNotFoundException;
	
	Countries getCountriesCode(String CountriesId) throws RecordNotFoundException;
	
	Countries updateCountries(Countries countries) throws ResourceNotFoundException, RecordNotFoundException;
	
	void deleteCountries(String CountriesId ) throws RecordNotFoundException;
	
	String deleteCountriesPartially(String CountriesId) throws NoSuchElementException, AttchmentsRecordNotFoundException;


}
