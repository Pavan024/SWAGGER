package com.testoper.bankservice.exception;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */

public class InactiveAccountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InactiveAccountException(String message) {
		super(message);
	}

}