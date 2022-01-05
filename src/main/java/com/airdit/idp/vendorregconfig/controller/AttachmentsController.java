package com.airdit.idp.vendorregconfig.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.DescriptionNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.NoOfParamFilesNotMatchException;
import com.airdit.idp.vendorregconfig.Exception.RecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.ResourceNotFoundException;
import com.airdit.idp.vendorregconfig.model.AttachmentModels;
import com.airdit.idp.vendorregconfig.model.Attachments;
import com.airdit.idp.vendorregconfig.service.AttachmentsService;

@RestController
@RequestMapping("/attachments")
@Validated
public class AttachmentsController {

	@Autowired
	private AttachmentsService attachmentsService;

	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

	/*
	 * public AttachmentsController(AttachmentsService attachmentsService) {
	 * super(); this.attachmentsService = attachmentsService; }
	 */
	@GetMapping("/attachmentslist")
	public List<Attachments> getAttachments()
			throws RecordNotFoundException, DataAccessException, AttchmentsRecordNotFoundException {
		return this.attachmentsService.getAttachments();

	}

	@GetMapping("/attachments/{attachmentsid}")
	public Attachments getAttachmsents(@PathVariable("attachmentsid") @NotBlank @Size(max = 10) String attachmentid)
			throws RecordNotFoundException, DataAccessException, AttchmentsRecordNotFoundException {
		return this.attachmentsService.getAttachmentId(attachmentid);

	}

	@PostMapping("/attachments")
	public List<Attachments> insertAttachments(@RequestParam("file") List<MultipartFile> multipartFiles,
			@RequestParam("attachmentmodels") AttachmentModels attachmentmodels)
			throws DataAccessException, RecordNotFoundException, ResourceNotFoundException,
			DescriptionNotFoundException, AttchmentsRecordNotFoundException, NoOfParamFilesNotMatchException {
		return this.attachmentsService.insert(multipartFiles, attachmentmodels);

	}

	@PutMapping("/attachments")
	public List<Attachments> updateAttachments(@RequestParam("file") MultipartFile multipartFiles,
			@RequestParam("attachmentmodels") Attachments attachmentmodels)
			throws DataAccessException, RecordNotFoundException, ResourceNotFoundException,
			DescriptionNotFoundException, AttchmentsRecordNotFoundException, NoOfParamFilesNotMatchException {
		return this.attachmentsService.updateAttachments(multipartFiles, attachmentmodels);

	}

	@DeleteMapping("/attachments/{attachmentid}")
	public void deleteAttachments(@PathVariable("attachmentid") String attachmentid)
			throws NoSuchElementException, DataAccessException, AttchmentsRecordNotFoundException {
		this.attachmentsService.deleteAttachments(attachmentid);
	}

	@DeleteMapping("/deleteAttachmentsPartially/{attachmentid}")
	public String deleteAttachmentsPartially(@PathVariable("attachmentid") String attachmentid)
			throws DataAccessException, AttchmentsRecordNotFoundException, NoSuchElementException {
		return attachmentsService.deleteAttachmentsPartially(attachmentid);
	}

}
