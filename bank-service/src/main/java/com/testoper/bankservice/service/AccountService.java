package com.testoper.bankservice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testoper.bankservice.common.Constants;
import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.entity.Bank;
import com.testoper.bankservice.exception.NotFoundException;
import com.testoper.bankservice.mapper.AccountMapper;
import com.testoper.bankservice.repository.AccountRepository;
import com.testoper.bankservice.request.CreateAccountRequest;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
@Service
public class AccountService {

	@Autowired
	BankService bankService;

	@Autowired
	AccountMapper accountMapper;

	@Autowired
	AccountRepository accountRepository;

	private static final Logger log = LoggerFactory.getLogger(AccountService.class);

	/**
	 * Creates an Account in the Bank
	 * 
	 * @param createAccountRequest
	 * @return
	 */
	public Account createAccount(CreateAccountRequest createAccountRequest, Long bankId) {
		Bank bank = bankService.getBank(bankId);
		Account account = accountMapper.mapCreateAccountRequestToAccount(createAccountRequest, bank);
		log.info("Creating Bank Account");
		Account createdAccount = accountRepository.save(account);
		log.info("Bank Account created");
		return createdAccount;
	}

	/**
	 * Gets the Account Details
	 * 
	 * @param id
	 * @param accountId
	 * @return
	 */
	public Account getAccount(Long bankId, Long accountId) {
		Bank bank = bankService.getBank(bankId);
		Optional<Account> account = accountRepository.findByIdAndBank(accountId, bank);
		if (!account.isPresent()) {
			throw new NotFoundException(Constants.ACCOUNT_NOT_FOUND);
		}
		return account.get();
	}

}
