package com.synch.image.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleException(Exception ex) {
		ex.printStackTrace();
		ErrorMessage error = new ErrorMessage(ex.getMessage(), "Some thing went wrong please try again");
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ErrorMessage> handleConstraintVoilationException(Exception ex) {
		ErrorMessage error = new ErrorMessage(ex.getMessage(), "username alerady exist, please choose different username");
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MandatoryParamtersMissing.class)
	public final ResponseEntity<ErrorMessage> handleNotNullandMandatoryParatmerException(MandatoryParamtersMissing ex) {
		ErrorMessage error = new ErrorMessage(ex.getMessage(), "Madatory paramters are missing");
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidUserNameandPassword.class)
	public final ResponseEntity<ErrorMessage> handleInvalidUserNameandPasswordException(InvalidUserNameandPassword ex) {
		ErrorMessage error = new ErrorMessage(ex.getMessage(), "Invalid usernae and password");
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidFileException.class)
	public final ResponseEntity<ErrorMessage> handleInvalidFileException(InvalidFileException ex) {
		ErrorMessage error = new ErrorMessage(ex.getMessage(), "Invalid usernae and password");
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
	}
	
}
