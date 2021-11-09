package com.testoper.bankservice.mapper;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.entity.Bank;
import com.testoper.bankservice.entity.Status;
import com.testoper.bankservice.request.CreateAccountRequest;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@Service
public class AccountMapper {

	/**
	 * 
	 * Maps Create Account Request to Account Entity
	 * 
	 * @param createAccountRequest
	 * @param bank
	 * @return
	 */
	public Account mapCreateAccountRequestToAccount(CreateAccountRequest createAccountRequest, Bank bank) {
		return new Account(generateAccountNumber(bank.getCode(), createAccountRequest.getCustomerId()),
				createAccountRequest.getType(), Status.valueOf(createAccountRequest.getStatus()),
				createAccountRequest.getAccountHolderName(), createAccountRequest.getCustomerId(),
				createAccountRequest.getCategoryCode(), 0.0, createAccountRequest.isNetBanking(), bank);
	}

	/**
	 * 
	 * Generates Account Number based on Bank Code, Customer Id and Current time
	 * 
	 * @param bankCode
	 * @param customerId
	 * @return
	 */
	private String generateAccountNumber(String bankCode, String customerId) {
		return (String.valueOf(Math.abs(bankCode.hashCode())) + String.valueOf(Math.abs(customerId.hashCode()))
				+ String.valueOf(Instant.now().getEpochSecond())).substring(0, 15);
	}

}
