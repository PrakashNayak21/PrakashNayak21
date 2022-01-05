package com.airdit.idp.vendorregconfig.service;

import java.util.List;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.RecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.ResourceNotFoundException;
import com.airdit.idp.vendorregconfig.model.NatureOfSupplier;

public interface NatureOfSupplierService {

	NatureOfSupplier insert(NatureOfSupplier natureOfSupplier) throws ResourceNotFoundException;

	NatureOfSupplier getNatureCode(String NatureCode) throws RecordNotFoundException;

	NatureOfSupplier updateNatureOfSupplier(NatureOfSupplier natureOfSupplier) throws RecordNotFoundException;

	void deleteNatureOfSupplier(String NatureCode) throws RecordNotFoundException;

	List<NatureOfSupplier> getNatureOfSupplier() throws AttchmentsRecordNotFoundException;

	String deleteNatureOfSupplierPartially(String NatureCode) throws RecordNotFoundException;
}
