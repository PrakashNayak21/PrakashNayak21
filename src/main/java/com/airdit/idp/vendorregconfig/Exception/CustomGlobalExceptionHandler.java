package com.airdit.idp.vendorregconfig.Exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

	@Autowired
	MessageSource messageBundel;
	@Autowired
	ResourceBundle bundel;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.error("{method.arguement.invalid}", ex.getMessage());
		System.out.println(ex.getMessage() + "testhhh");
		HashMap<String, Object> body = new HashMap<String, Object>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public HashMap<String, String> handleConstraintViolation(ConstraintViolationException ex) {

		HashMap<String, String> errors = new HashMap<>();

		ex.getConstraintViolations().forEach(cv -> {
			errors.put("message", cv.getMessage());
			errors.put("path", (cv.getPropertyPath()).toString());
		});
		logger.error("{method.arguement.invalid}", ex.getMessage());
		return errors;
	}

	@ExceptionHandler(value = DataAccessException.class)
	public ResponseEntity<Object> exception(DataAccessException ex) {
		logger.error(bundel.getString("record.id.not.Exist"), ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Object> exception(ResourceNotFoundException ex) {
		logger.error(bundel.getString("database.connection.error"), ex.getMessage());
		return new ResponseEntity<>(bundel.getString("database.connection.error;' "), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<Object> exception(RecordNotFoundException ex) {
		logger.error(bundel.getString("record.id.not.Exist"), ex.getMessage());
		Locale.setDefault(new Locale("in", "IN"));
		return new ResponseEntity<>(bundel.getString("record.id.not.Exist"), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = DescriptionNotFoundException.class)
	public ResponseEntity<Object> exception(DescriptionNotFoundException ex) {
		logger.error(bundel.getString("attachments.description.NotBlank"), ex.getMessage());
		Locale.setDefault(new Locale("in", "IN"));
		return new ResponseEntity<>(bundel.getString("attachments.description.NotBlank"), HttpStatus.NOT_FOUND);
//		return new ResponseEntity<>("Data Access", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = AttchmentsRecordNotFoundException.class)
	public ResponseEntity<Object> exception(AttchmentsRecordNotFoundException ex) {
		logger.error(bundel.getString("attchments.record.id.doesnot.Exist"), ex.getMessage());
		Locale.setDefault(new Locale("in", "IN"));
		return new ResponseEntity<>(bundel.getString("attchments.record.id.doesnot.Exist"), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = RecordDeleteException.class)
	public ResponseEntity<Object> exception(RecordDeleteException ex) {
		logger.error(bundel.getString("attchments.record.id.doesnot.Exist"), ex.getMessage());
		Locale.setDefault(new Locale("in", "IN"));
		return new ResponseEntity<>(bundel.getString("attchments.record.id.doesnot.Exist"), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<Object> exception(NoSuchElementException ex) {
		logger.error(bundel.getString("attchments.record.id.doesnot.Exist"), ex.getMessage());
		Locale.setDefault(new Locale("in", "IN"));
		return new ResponseEntity<>(bundel.getString("attchments.record.id.doesnot.Exist"), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = NoOfParamFilesNotMatchException.class)
	public ResponseEntity<Object> exception(NoOfParamFilesNotMatchException ex) {
		logger.error(bundel.getString("noffilesarg.exist"), ex.getMessage());
		Locale.setDefault(new Locale("in", "IN"));
		return new ResponseEntity<>(bundel.getString("noffilesarg.exist"), HttpStatus.NOT_FOUND);
	}

}
