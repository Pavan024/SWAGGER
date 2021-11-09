package com.testoper.bankservice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testoper.bankservice.common.Constants;
import com.testoper.bankservice.exception.InactiveAccountException;
import com.testoper.bankservice.exception.InsufficientBalanceException;
import com.testoper.bankservice.repository.AccountRepository;
import com.testoper.bankservice.util.DataBuilder;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

	@InjectMocks
	TransactionService transactionService;

	@Mock
	AccountService accountService;

	@Mock
	AccountRepository accountRepository;

	@Test
	public void testWithdraw() {
		when(accountService.getAccount(Mockito.anyLong(), Mockito.anyLong())).thenReturn(DataBuilder.getMockAccount());
		when(accountRepository.save(Mockito.any())).thenReturn(DataBuilder.getMockAccount());
		Assertions.assertEquals(DataBuilder.getMockAccount().getId(),
				transactionService.withdraw(DataBuilder.getMockTransactionRequest()).getId());
	}

	@Test
	public void testWithdrawWithInsufficientBalance() {
		when(accountService.getAccount(Mockito.anyLong(), Mockito.anyLong())).thenReturn(DataBuilder.getMockAccount());
		Exception exception = assertThrows(InsufficientBalanceException.class, () -> {
			transactionService.withdraw(DataBuilder.getMockTransactionRequestWithInsufficientBalance());
		});
		Assertions.assertEquals(Constants.INSUFFICIENT_ACCOUNT_BALANCE, exception.getMessage());
	}

	@Test
	public void testWithdrawWithInactiveAccount() {
		when(accountService.getAccount(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(DataBuilder.getMockInactiveAccount());
		Exception exception = assertThrows(InactiveAccountException.class, () -> {
			transactionService.withdraw(DataBuilder.getMockTransactionRequest());
		});
		Assertions.assertEquals(Constants.INACTIVE_BANK_ACCOUNT, exception.getMessage());
	}

	@Test
	public void testDeposit() {
		when(accountService.getAccount(Mockito.anyLong(), Mockito.anyLong())).thenReturn(DataBuilder.getMockAccount());
		when(accountRepository.save(Mockito.any())).thenReturn(DataBuilder.getMockAccount());
		Assertions.assertEquals(DataBuilder.getMockAccount().getId(),
				transactionService.deposit(DataBuilder.getMockTransactionRequest()).getId());
	}

	@Test
	public void testDepositWithInactiveAccount() {
		when(accountService.getAccount(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(DataBuilder.getMockInactiveAccount());
		Exception exception = assertThrows(InactiveAccountException.class, () -> {
			transactionService.deposit(DataBuilder.getMockTransactionRequest());
		});
		Assertions.assertEquals(Constants.INACTIVE_BANK_ACCOUNT, exception.getMessage());
	}
}
