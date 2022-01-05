package com.airdit.idp.vendorregconfig.serviceimpl;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.DescriptionNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.NoOfParamFilesNotMatchException;
import com.airdit.idp.vendorregconfig.attachments.util.DatabaseRecordfindUtil;
import com.airdit.idp.vendorregconfig.model.AttachmentModels;
import com.airdit.idp.vendorregconfig.model.Attachments;
import com.airdit.idp.vendorregconfig.repository.AttachmentsRepository;
import com.airdit.idp.vendorregconfig.service.AttachmentsService;

@Service
public class AttachmentConfigService implements AttachmentsService {
	DatabaseRecordfindUtil attachmentutil = new DatabaseRecordfindUtil();

	@Autowired
	private AttachmentsRepository attachmentsRepository;

	/*
	 * public AttachmentConfigService(AttachmentsRepository attachmentsRepository) {
	 * this.attachmentsRepository=attachmentsRepository; }
	 */
	@Override
	public Attachments getAttachmentId(String attachmentid)
			throws AttchmentsRecordNotFoundException, DataAccessException {

		Optional<Attachments> attachments = (attachmentsRepository.findById(attachmentid));
		Attachments attachment = attachmentsRepository.findById(attachmentid).get();
		if (attachments.isPresent() && attachment.isDelete() != true) {
			System.out.println(attachment.getAttachmentId());
			return attachment;

		} else {
			throw new AttchmentsRecordNotFoundException("{attchments.record.id.doesnot.Exist}");
		}

	}

	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

	@Override
	public List<Attachments> insert(List<MultipartFile> multipartFiles, AttachmentModels attachmentmodels)
			throws AttchmentsRecordNotFoundException, DataAccessException, DescriptionNotFoundException,
			NoOfParamFilesNotMatchException {
		// TODO Auto-generated method stub
		List<Attachments> attachmentList = new ArrayList<Attachments>();
		if (multipartFiles.size() != attachmentmodels.getAttachments().size())
			throw new NoOfParamFilesNotMatchException("{noffilesarg.exist}");
		for (MultipartFile multipartFile : multipartFiles) {
			String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename()).toLowerCase();
			if (extension.equals("pdf")) {
				int index = multipartFiles.indexOf(multipartFile);
				String descr = attachmentmodels.getAttachments().get(index).getDescription();
				if (attachmentmodels == null || descr.length() == 0)
					throw new DescriptionNotFoundException("{attachments.description.NotBlank}");
				String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
				try {
					copy(multipartFile.getInputStream(), fileStorage, REPLACE_EXISTING);
					String filepath = DIRECTORY + filename;
					Attachments attachments = new Attachments(descr);
					attachments.setMandatory(true);
					attachments.setDocName(filepath);
					attachments.setDocType(multipartFile.getContentType());
//					attachments.setDateofmodification(new Date(System.currentTimeMillis()));
					attachmentsRepository.save(attachments);
					attachmentList.add(attachments);
				} catch (IOException e) {
					return null;
				}
			}
		}
		return attachmentList;

	}

	@Override
	public List<Attachments> getAttachments() throws AttchmentsRecordNotFoundException, DataAccessException {

		List<Attachments> attachmentsList = attachmentsRepository.findAll();

		if (attachmentsList.size() > 0) {

			List<Attachments> attachmentslist = attachmentutil
					.checkAttachmentrecordAvilableinDatabaseTable(attachmentsList);
			if (attachmentslist != null && attachmentslist.size() > 0) {
				return attachmentslist;
			} else {
				throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
			}
		} else {
			throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
		}

	}

	@Override
	public List<Attachments> updateAttachments(MultipartFile multipartFiles, Attachments attachmentmodels)
			throws AttchmentsRecordNotFoundException, DescriptionNotFoundException, NoOfParamFilesNotMatchException {

		List<Attachments> attachmentList = new ArrayList<Attachments>();
//		if (multipartFiles.size() != attachmentmodels.getAttachments().size())
//			throw new NoOfParamFilesNotMatchException("{noffilesarg.exist}");
//		for (MultipartFile multipartFile : multipartFiles) {
		String extension = FilenameUtils.getExtension(multipartFiles.getOriginalFilename()).toLowerCase();
		if (extension.equals("pdf")) {
			String descr = attachmentmodels.getDescription();
			String attachmentid = attachmentmodels.getAttachmentId();
			if (!attachmentsRepository.getOne(attachmentid).isDelete()) {
				if (descr == null || descr.length() == 0 || attachmentid == null) {
					throw new DescriptionNotFoundException("please fill the blank file");
				} else if (attachmentsRepository.getOne(attachmentid) == null) {
					throw new AttchmentsRecordNotFoundException("{attchments.record.id.doesnot.Exist}");
				} else {
					String filename = StringUtils.cleanPath(multipartFiles.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					try {
						copy(multipartFiles.getInputStream(), fileStorage, REPLACE_EXISTING);
						String filepath = DIRECTORY + filename;
						Attachments attachments = new Attachments(descr, attachmentid);
						attachments.setMandatory(true);
						attachments.setDocName(filepath);
						attachments.setDocType(multipartFiles.getContentType());
//							attachments.setDateofmodification(new Date(System.currentTimeMillis()));
						attachmentsRepository.save(attachments);
						attachmentList.add(attachments);
					} catch (IOException e) {
						return null;
					}
				}
			}

		}
//		}
		return attachmentList;
	}

	@Override
	public void deleteAttachments(String attachmentid) throws AttchmentsRecordNotFoundException, DataAccessException {
		Optional<Attachments> attachments = Optional.ofNullable(attachmentsRepository.getOne(attachmentid));
		if (attachments.isPresent()) {
			attachmentsRepository.deleteById(attachmentid);
			System.out.println(attachments.toString());
		} else {
			throw new AttchmentsRecordNotFoundException("{attchments.record.id.doesnot.Exist}");
		}

	}

	@Override
	public String deleteAttachmentsPartially(String attachmentid)
			throws AttchmentsRecordNotFoundException, DataAccessException, NoSuchElementException {
		Optional<Attachments> existattachments = attachmentsRepository.findById(attachmentid);
		if (existattachments.isPresent()) {
			if (!existattachments.get().isDelete()) {
				existattachments.get().setDelete(true);
				attachmentsRepository.save(existattachments.get());
				return "RECORD DELETED SUCCESSFULLY";
			} else {
				throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
			}
		} else {
			throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
		}

	}

}
