package com.airdit.idp.vendorregconfig.serviceimpl;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.RecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.ResourceNotFoundException;
import com.airdit.idp.vendorregconfig.attachments.util.DatabaseRecordfindUtil;
import com.airdit.idp.vendorregconfig.model.NatureOfSupplier;
import com.airdit.idp.vendorregconfig.repository.NatureOfSupplierRepository;
import com.airdit.idp.vendorregconfig.service.NatureOfSupplierService;

@Service
public class NatureOfSupplierConfigService implements NatureOfSupplierService {
	@Autowired
	private NatureOfSupplierRepository natureOfSupplierRepository;
	DatabaseRecordfindUtil NatureOfSuppliersutil=new DatabaseRecordfindUtil();

	@Transactional
	@Override
	public NatureOfSupplier insert(NatureOfSupplier natureOfSupplier) throws ResourceNotFoundException {
//		natureOfSupplier.setDateofmodification(new Date(System.currentTimeMillis()));
		Optional<NatureOfSupplier> existNatureOfSupplier = Optional
				.ofNullable(natureOfSupplierRepository.save(natureOfSupplier));
		if (existNatureOfSupplier.isPresent()) {
			return existNatureOfSupplier.get();
		} else {
			throw new ResourceNotFoundException("{database.connection.error}");
		}
	}

	@Transactional
	@Override
	public NatureOfSupplier getNatureCode(String NatureCode) throws RecordNotFoundException,NoSuchElementException {
		Optional<NatureOfSupplier> natureOfSuppliers = (natureOfSupplierRepository.findById(NatureCode));
		NatureOfSupplier natureOfSupplier = natureOfSupplierRepository.findById(NatureCode).get();
		if (natureOfSuppliers.isPresent() && natureOfSupplier.isDelete()!= true) {
	        System.out.println(natureOfSupplier.getNatureCode()); 
			return natureOfSupplier;
	          
		}
		
		else {
			throw new RecordNotFoundException("{record.id.not.Exist}");
		}
	}

	@Transactional
	@Override
	public void deleteNatureOfSupplier(String NatureCode) throws RecordNotFoundException {
		Optional<NatureOfSupplier> deleteNatureOfSupplier = (natureOfSupplierRepository.findById(NatureCode));
		if (deleteNatureOfSupplier.isPresent()) {
			natureOfSupplierRepository.deleteById(NatureCode);
			System.out.println(NatureCode + " id is deleted");
		} else {
			throw new RecordNotFoundException("{record.id.not.Exist}");
		}
	}

	@Transactional
	@Override
	public NatureOfSupplier updateNatureOfSupplier(NatureOfSupplier natureOfSupplier) throws RecordNotFoundException {
//		natureOfSupplier.setDateofmodification(new Date(System.currentTimeMillis()));
		Optional<Optional<NatureOfSupplier>> existlegalStatus = Optional
				.ofNullable(natureOfSupplierRepository.findById(natureOfSupplier.getNatureCode()));

		if (existlegalStatus.isPresent()) {
			return natureOfSupplierRepository.save(natureOfSupplier);
		} else {
			throw new RecordNotFoundException("{record.id.not.Exist}");
		}
	}

	@Transactional
	@Override
	public List<NatureOfSupplier> getNatureOfSupplier() throws AttchmentsRecordNotFoundException {
		List<NatureOfSupplier> NatureOfSupplierList = natureOfSupplierRepository.findAll();

		if (NatureOfSupplierList.size() > 0) {

			List<NatureOfSupplier>NatureOfSupplierlist = NatureOfSuppliersutil
					.CheckNatureOfSupplierRecordAvilableinDatabaseTable(NatureOfSupplierList);
			if (NatureOfSupplierlist != null && NatureOfSupplierlist.size() > 0) {
				return NatureOfSupplierlist;
			} else {
				throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
			}
		} else {
			throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
		}
	}

	@Override
	public String deleteNatureOfSupplierPartially(String NatureCode) throws RecordNotFoundException,NoSuchElementException {
		Optional<NatureOfSupplier> natureOfSuppliers = Optional.ofNullable(natureOfSupplierRepository.getOne(NatureCode));
		NatureOfSupplier natureOfSupplier = natureOfSupplierRepository.findById(NatureCode).get();
		if (natureOfSuppliers.isPresent() && !natureOfSupplier.isDelete()) {
			natureOfSupplier.setDelete(true);

			natureOfSupplierRepository.save(natureOfSupplier);
			return "record delete sucessfully";

		} else if (natureOfSupplier != null && natureOfSupplier.isDelete() == true) {
			return "no record found";
		}

		else {
			throw new RecordNotFoundException("{attchments.record.id.doesnot.Exist}");
		}

	}
}
