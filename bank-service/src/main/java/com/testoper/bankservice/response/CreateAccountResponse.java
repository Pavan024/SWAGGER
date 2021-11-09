package com.testoper.bankservice.response;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
public class CreateAccountResponse {
	private Long id;
	private String accountNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
