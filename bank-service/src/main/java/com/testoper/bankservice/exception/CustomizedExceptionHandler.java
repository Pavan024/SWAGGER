package com.testoper.bankservice.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.WordUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.testoper.bankservice.common.Constants;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(WebRequest request, Exception ex) {
		if (ex.getCause() instanceof BadCredentialsException) {
			return sendResponse(request, ex, HttpStatus.UNAUTHORIZED);
		}
		return sendResponse(request, ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(Constants.VALIDATION_FAILED, errors,
				status.value(), Instant.now().toString(), request.getDescription(false), status));
	}

	private ResponseEntity<ErrorResponse> sendResponse(WebRequest request, Exception ex, HttpStatus httpStatus) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		ErrorResponse errorRecord = new ErrorResponse(Constants.FAILED, errors, httpStatus.value(),
				Instant.now().toString(), request.getDescription(false), httpStatus);
		return ResponseEntity.status(httpStatus).body(errorRecord);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleNotFoundException(WebRequest request, NotFoundException ex) {
		return sendResponse(request, ex, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public final ResponseEntity<ErrorResponse> handleInsufficientBalanceException(WebRequest request,
			InsufficientBalanceException ex) {
		return sendResponse(request, ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InactiveAccountException.class)
	public final ResponseEntity<ErrorResponse> handleInactiveAccountException(WebRequest request,
			InactiveAccountException ex) {
		return sendResponse(request, ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(WebRequest request,
			DataIntegrityViolationException ex) {
		List<String> errors = new ArrayList<>();
		if (ex.getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex.getCause();
			String constraintName = constraintViolationException.getConstraintName();
			errors.add(WordUtils.capitalizeFully(constraintName.replaceAll("_", " ")) + " should be Unique");
		}
		ErrorResponse errorRecord = new ErrorResponse(Constants.FAILED, errors, HttpStatus.CONFLICT.value(),
				Instant.now().toString(), request.getDescription(false), HttpStatus.CONFLICT);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorRecord);
	}
}
