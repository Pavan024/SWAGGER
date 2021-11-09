package com.testoper.bankservice.response;

import com.testoper.bankservice.entity.Status;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
public class AccountResponse {
	private Long id;
	private String accountNumber;
	private String type;
	private Status status;
	private String accountHolderName;
	private String customerId;
	private String categoryCode;
	private Double balance;
	private boolean isNetBanking;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public boolean isNetBanking() {
		return isNetBanking;
	}

	public void setNetBanking(boolean isNetBanking) {
		this.isNetBanking = isNetBanking;
	}

}
