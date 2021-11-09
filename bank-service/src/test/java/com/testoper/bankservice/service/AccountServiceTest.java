package com.testoper.bankservice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testoper.bankservice.common.Constants;
import com.testoper.bankservice.exception.NotFoundException;
import com.testoper.bankservice.mapper.AccountMapper;
import com.testoper.bankservice.repository.AccountRepository;
import com.testoper.bankservice.util.DataBuilder;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	@InjectMocks
	AccountService accountService;

	@Mock
	AccountRepository accountRepository;

	@Mock
	BankService bankService;

	@Mock
	AccountMapper accountMapper;

	@BeforeEach
	public void setup() {
		when(bankService.getBank(Mockito.anyLong())).thenReturn(DataBuilder.getMockBank());
	}

	@Test
	public void testCreateAccount() {
		when(accountRepository.save(Mockito.any())).thenReturn(DataBuilder.getMockAccount());
		Assertions.assertEquals(DataBuilder.getMockAccount().getId(),
				accountService.createAccount(DataBuilder.getMockCreateAccountRequest(), 25L).getId());
	}

	@Test
	public void testGetAccount() {
		when(accountRepository.findByIdAndBank(Mockito.anyLong(), Mockito.any()))
				.thenReturn(Optional.of(DataBuilder.getMockAccount()));
		Assertions.assertEquals(DataBuilder.getMockAccount().getId(), accountService.getAccount(25L, 43L).getId());
	}

	@Test
	public void testGetBankWithNotFoundException() {
		when(accountRepository.findByIdAndBank(Mockito.anyLong(), Mockito.any())).thenReturn(Optional.empty());
		Exception exception = assertThrows(NotFoundException.class, () -> {
			accountService.getAccount(64L, 87L);
		});
		Assertions.assertEquals(Constants.ACCOUNT_NOT_FOUND, exception.getMessage());
	}
}
