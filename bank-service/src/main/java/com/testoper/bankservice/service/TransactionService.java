package com.testoper.bankservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testoper.bankservice.common.Constants;
import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.entity.Status;
import com.testoper.bankservice.exception.InactiveAccountException;
import com.testoper.bankservice.exception.InsufficientBalanceException;
import com.testoper.bankservice.repository.AccountRepository;
import com.testoper.bankservice.request.TransactionRequest;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@Service
public class TransactionService {
	@Autowired
	BankService bankService;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountRepository accountRepository;

	private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

	/**
	 * 
	 * Withdraws Money from Bank Account
	 * 
	 * @param transactionRequest
	 * @return
	 */
	public Account withdraw(TransactionRequest transactionRequest) {
		Account account = accountService.getAccount(transactionRequest.getBankId(), transactionRequest.getAccountId());
		checkAccountActive(account);
		Double currentBalance = account.getBalance();
		if (transactionRequest.getTransactionAmount() > currentBalance) {
			throw new InsufficientBalanceException(Constants.INSUFFICIENT_ACCOUNT_BALANCE);
		}
		account.setBalance(currentBalance - transactionRequest.getTransactionAmount());
		log.info("Withdrawing money from Bank Account");
		return accountRepository.save(account);
	}

	/**
	 * Deposits Money to Bank Account
	 * 
	 * @param transactionRequest
	 * @return
	 */
	public Account deposit(TransactionRequest transactionRequest) {
		Account account = accountService.getAccount(transactionRequest.getBankId(), transactionRequest.getAccountId());
		checkAccountActive(account);
		account.setBalance(account.getBalance() + transactionRequest.getTransactionAmount());
		log.info("Depositing money to Bank Account");
		return accountRepository.save(account);
	}

	/**
	 * 
	 * Throws INACTIVE_BANK_ACCOUNT Error if the Bank is INACTIVE
	 * 
	 * @param account
	 */
	private void checkAccountActive(Account account) {
		if (account.getStatus() == Status.INACTIVE) {
			throw new InactiveAccountException(Constants.INACTIVE_BANK_ACCOUNT);
		}
	}
}
