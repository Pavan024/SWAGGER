package com.testoper.bankservice.util;

import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.entity.Bank;
import com.testoper.bankservice.entity.Status;
import com.testoper.bankservice.request.CreateAccountRequest;
import com.testoper.bankservice.request.CreateBankRequest;
import com.testoper.bankservice.request.TransactionRequest;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
public class DataBuilder {

	public static Bank getMockBank() {
		Bank bank = new Bank();
		bank.setId(25L);
		bank.setAddress("Hyderabad");
		bank.setCode("HDFCX");
		bank.setEmail("hyderabadbank@hdfc.com");
		bank.setPhoneNumber("9876787656");
		bank.setName("HDFC Bank");
		return bank;
	}

	public static CreateBankRequest getMockCreateBankRequest() {
		CreateBankRequest createBankRequest = new CreateBankRequest();
		createBankRequest.setAddress("Hyderabad");
		createBankRequest.setCode("HDFCX");
		createBankRequest.setEmail("hyderabadbank@hdfc.com");
		createBankRequest.setName("HDFC Bank");
		createBankRequest.setPhoneNumber("9876787656");
		return createBankRequest;
	}

	public static Account getMockAccount() {
		Account account = new Account();
		account.setAccountHolderName("Regina");
		account.setAccountNumber("6758415094121621587842");
		account.setBalance(50000.0);
		account.setBank(getMockBank());
		account.setId(555L);
		account.setCategoryCode("SAVNG");
		account.setCustomerId("86348643");
		account.setNetBanking(true);
		account.setStatus(Status.ACTIVE);
		account.setType("Savings");
		return account;
	}
	
	public static Account getMockInactiveAccount() {
		Account account = new Account();
		account.setAccountHolderName("Robert");
		account.setAccountNumber("6758415094121621587842");
		account.setBalance(50000.0);
		account.setBank(getMockBank());
		account.setId(555L);
		account.setCategoryCode("SAVNG");
		account.setCustomerId("86348643");
		account.setNetBanking(true);
		account.setStatus(Status.INACTIVE);
		account.setType("Savings");
		return account;
	}

	public static CreateAccountRequest getMockCreateAccountRequest() {
		CreateAccountRequest createAccountRequest = new CreateAccountRequest();
		createAccountRequest.setAccountHolderName("Robert");
		createAccountRequest.setCategoryCode("SAVNG");
		createAccountRequest.setType("Savings");
		createAccountRequest.setCustomerId("86348643");
		createAccountRequest.setStatus("ACTIVE");
		createAccountRequest.setNetBanking(true);
		return createAccountRequest;
	}
	

	public static TransactionRequest getMockTransactionRequest() {
		TransactionRequest transactionRequest = new TransactionRequest();
		transactionRequest.setAccountId(132L);
		transactionRequest.setBankId(76L);
		transactionRequest.setTransactionAmount(15000.00);
		return transactionRequest;
	}

	public static TransactionRequest getMockTransactionRequestWithInsufficientBalance() {
		TransactionRequest transactionRequest = new TransactionRequest();
		transactionRequest.setAccountId(132L);
		transactionRequest.setBankId(76L);
		transactionRequest.setTransactionAmount(60000.00);
		return transactionRequest;
	}

}
