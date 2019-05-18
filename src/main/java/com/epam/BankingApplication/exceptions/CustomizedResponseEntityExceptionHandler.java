package com.epam.BankingApplication.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param exception Exception
	 * @param request   WebRequest
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param exception Exception
	 * @param request   WebRequest
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * @param exception Exception
	 * @param request   WebRequest
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(UsersNotFoundException.class)
	public final ResponseEntity<Object> handleUsersNotFoundException(UsersNotFoundException exception,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
