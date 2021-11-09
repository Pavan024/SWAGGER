package com.testoper.bankservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
@Entity
@Table(name = "ACCOUNT")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name = "ACCOUNT_HOLDER_NAME")
	private String accountHolderName;
	@Column(name = "CUSTOMER_ID")
	private String customerId;
	@Column(name = "CATEGORY_CODE")
	private String categoryCode;
	@Column(name = "BALANCE")
	private Double balance;
	@Column(name = "IS_NET_BANKING")
	private boolean isNetBanking;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BANK_ID", nullable=false)
	private Bank bank;
	
	public Account() {

	}

	public Account(String accountNumber, String type, Status status, String accountHolderName, String customerId,
			String categoryCode, Double balance, boolean isNetBanking, Bank bank) {
		super();
		this.accountNumber = accountNumber;
		this.type = type;
		this.status = status;
		this.accountHolderName = accountHolderName;
		this.customerId = customerId;
		this.categoryCode = categoryCode;
		this.balance = balance;
		this.isNetBanking = isNetBanking;
		this.bank = bank;
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

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
