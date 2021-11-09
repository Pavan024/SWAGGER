package com.testoper.paymentservice.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.gson.Gson;
import com.testoper.paymentservice.common.Constants;

import feign.FeignException;

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

	@ExceptionHandler(FeignException.class)
	public final ResponseEntity<ErrorResponse> handleFeignException(WebRequest request, FeignException feignException) {
		List<String> errors = new ArrayList<>();
		Gson gson = new Gson();
		ErrorResponse errorResponse = null;
		if (feignException.status() == -1) {
			errors.add("Bank Service is Unavailable. Unable to Connect! Please try after sometime.");
			errorResponse = new ErrorResponse(Constants.FAILED, errors, HttpStatus.SERVICE_UNAVAILABLE.value(),
					Instant.now().toString(), request.getDescription(false), HttpStatus.SERVICE_UNAVAILABLE);
		} else {
			errorResponse = gson.fromJson(feignException.contentUTF8(), ErrorResponse.class);
			errorResponse.setPath(request.getDescription(false));
			errorResponse.setTimestamp(Instant.now().toString());
		}
		return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
	}
}
