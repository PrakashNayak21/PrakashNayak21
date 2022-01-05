package com.airdit.idp.vendorregconfig.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.DescriptionNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.NoOfParamFilesNotMatchException;
import com.airdit.idp.vendorregconfig.Exception.RecordNotFoundException;
import com.airdit.idp.vendorregconfig.model.AttachmentModels;
import com.airdit.idp.vendorregconfig.model.Attachments;

public interface AttachmentsService {

	Attachments getAttachmentId(String attachmentid) throws  AttchmentsRecordNotFoundException, DataAccessException;

	List<Attachments> getAttachments() throws AttchmentsRecordNotFoundException, DataAccessException;

//	String deleteAttachments(String attachmentid) throws  AttchmentsRecordNotFoundException, DataAccessException;

	List<Attachments> insert(List<MultipartFile> multipartFiles, AttachmentModels attachmentmodels)
			throws  DataAccessException, DescriptionNotFoundException, AttchmentsRecordNotFoundException, NoOfParamFilesNotMatchException;

	List<Attachments> updateAttachments(MultipartFile multipartFiles, Attachments attachmentmodels)
			throws RecordNotFoundException, DescriptionNotFoundException, AttchmentsRecordNotFoundException, NoOfParamFilesNotMatchException;

//	String deleteAttachmentsPartially(String attachmentid) throws AttchmentsRecordNotFoundException, DataAccessException, NoSuchElementException;
      
//	String deleteAttachmentsPartially(String attachmentid);
	void deleteAttachments(String attachmentid )throws  AttchmentsRecordNotFoundException, DataAccessException;
	
	String deleteAttachmentsPartially(String attachmentid) throws AttchmentsRecordNotFoundException, DataAccessException, NoSuchElementException;
	
}
