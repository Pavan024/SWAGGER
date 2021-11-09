package com.testoper.bankservice.common;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
public class Constants {
	public static final String FAILED = "Failed";
	public static final String VALIDATION_FAILED = "Validation Failed";
	public static final String BANK_NOT_FOUND = "Bank Not Found with given Id";
	public static final String ACCOUNT_NOT_FOUND = "Account Not Found with given Id";
	public static final String INSUFFICIENT_ACCOUNT_BALANCE = "Insufficient Account Balance";
	public static final String INACTIVE_BANK_ACCOUNT = "Your Bank Account is Inactive";
	// Token Expires in 30 minutes
	public static final long JWT_TOKEN_VALIDITY = 30 * 60;
}