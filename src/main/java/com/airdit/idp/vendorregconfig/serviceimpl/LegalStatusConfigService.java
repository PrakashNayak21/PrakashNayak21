package com.airdit.idp.vendorregconfig.serviceimpl;

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
import com.airdit.idp.vendorregconfig.model.LegalStatus;
import com.airdit.idp.vendorregconfig.repository.LegalStatusRepository;
import com.airdit.idp.vendorregconfig.service.LegalStatusService;

@Service
public class LegalStatusConfigService implements LegalStatusService {
	DatabaseRecordfindUtil LegalStatusutil = new DatabaseRecordfindUtil();

	@Autowired
	private LegalStatusRepository legalStatusRepository;

	@Override
	public LegalStatus insert(LegalStatus legalStatus) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
//		legalStatus.setDateofmodification(new Date(System.currentTimeMillis()));
		Optional<LegalStatus> exisLegalStatus = Optional.ofNullable(legalStatusRepository.save(legalStatus));
		if (exisLegalStatus.isPresent()) {
			return exisLegalStatus.get();
		} else {
			throw new ResourceNotFoundException("{database.connection.error}");
		}
	}

	@Override
	public LegalStatus getStatusCode(String StatusCode) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<LegalStatus> legalStatus = (legalStatusRepository.findById(StatusCode));
		LegalStatus legalStatu = legalStatusRepository.findById(StatusCode).get();
		if (legalStatus.isPresent() && legalStatu.isDelete() != true) {
			System.out.println(legalStatu.getStatusCode());
			return legalStatu;

		} else {
			throw new RecordNotFoundException("{record.id.not.Exist}");
		}
	}

	@Transactional
	@Override
	public LegalStatus updateLegalStatus(LegalStatus legalStatus)
			throws ResourceNotFoundException, RecordNotFoundException {
//		legalStatus.setDateofmodification(new Date(System.currentTimeMillis()));
		Optional<Optional<LegalStatus>> existlegalStatus = Optional
				.ofNullable(legalStatusRepository.findById(legalStatus.getStatusCode()));

		if (existlegalStatus.isPresent()) {

			if (existlegalStatus.get().get().isDelete() != true && legalStatus.isDelete() != true) {
				return legalStatusRepository.save(legalStatus);
			} else {
				throw new RecordNotFoundException("{record.id.not.Exist}");
			}

		} else {
			throw new RecordNotFoundException("{record.id.not.Exist}");
		}

	}

	@Transactional
	@Override
	public void deleteLegalStatus(String StatusCode) throws RecordNotFoundException {
		// TODO Auto-generated method stub
//		legalStatusRepository.deleteById(null);
		Optional<LegalStatus> legalStatus = (legalStatusRepository.findById(StatusCode));
		if (legalStatus.isPresent()) {
			legalStatusRepository.deleteById(StatusCode);
			System.out.println(legalStatus + " id is deleted");
		} else {
			throw new RecordNotFoundException("{record.id.not.Exist}");
		}

	}

	@Transactional
	@Override
	public List<LegalStatus> getLegalStatus() throws AttchmentsRecordNotFoundException {
		List<LegalStatus> LegalStatusList = legalStatusRepository.findAll();

		if (LegalStatusList.size() > 0) {

			List<LegalStatus> LegalStatuslist = LegalStatusutil
					.checkLegalStatusrecordAvilableinDatabaseTable(LegalStatusList);
			if (LegalStatuslist != null && LegalStatuslist.size() > 0) {
				return LegalStatuslist;
			} else {
				throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
			}
		} else {
			throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
		}
	}

	@Override
	public String deleteLegalStatusPartially(String attachmentid)
			throws AttchmentsRecordNotFoundException, NoSuchElementException {
		Optional<LegalStatus> legalStatus = Optional.ofNullable(legalStatusRepository.getOne(attachmentid));
		LegalStatus legalStatu = legalStatusRepository.findById(attachmentid).get();
		if (legalStatus.isPresent() && !legalStatu.isDelete()) {
			legalStatu.setDelete(true);

			legalStatusRepository.save(legalStatu);
			return "record delete sucessfully";

		} else if (legalStatu != null && legalStatu.isDelete() == true) {
			return "no record found";
		}

		else {
			System.out.println("throw");
			throw new AttchmentsRecordNotFoundException("{attchments.record.id.doesnot.Exist}");
		}

	}
}
