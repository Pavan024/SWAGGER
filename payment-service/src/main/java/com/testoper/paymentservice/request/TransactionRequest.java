package com.testoper.paymentservice.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
public class TransactionRequest {
	@Min(value = 1, message = "Invalid Bank Id")
	@NotNull(message = "Bank Id should not be null")
	private Long bankId;
	@Min(value = 1, message = "Invalid Account Id")
	@NotNull(message = "Account Id should not be null")
	private Long accountId;
	@Min(value = 1, message = "Invalid Transaction Amount")
	@NotNull(message = "Transaction Amount should not be null")
	private Double transactionAmount;

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

}
