package com.testoper.paymentservice.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@Entity
@Table(name = "PAYMENT")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	@Column(name = "ACCOUNT_HOLDER_NAME")
	private String accountHolderName;
	@Column(name = "TRANSACTION_AMOUNT")
	private Double transactionAmount;
	@Column(name = "BANK_NAME")
	private String bankName;
	@Column(name = "DATE_TIME")
	private Instant date;

	public Payment() {

	}

	public Payment(String accountNumber, String accountHolderName, Double transactionAmount, String bankName,
			Instant date) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.transactionAmount = transactionAmount;
		this.bankName = bankName;
		this.date = date;
	}

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

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

}
