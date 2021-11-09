package com.testoper.bankservice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testoper.bankservice.common.Constants;
import com.testoper.bankservice.exception.NotFoundException;
import com.testoper.bankservice.repository.BankRepository;
import com.testoper.bankservice.util.DataBuilder;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
	@InjectMocks
	BankService bankService;

	@Mock
	BankRepository bankRepository;

	@Test
	public void testCreateBank() {
		when(bankRepository.save(Mockito.any())).thenReturn(DataBuilder.getMockBank());
		Assertions.assertEquals(DataBuilder.getMockBank().getId(),
				bankService.createBank(DataBuilder.getMockCreateBankRequest()).getId());
	}

	@Test
	public void testGetBank() {
		when(bankRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(DataBuilder.getMockBank()));
		Assertions.assertEquals(DataBuilder.getMockBank().getId(), bankService.getBank(25L).getId());
	}

	@Test
	public void testGetBankWithNotFoundException() {
		when(bankRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Exception exception = assertThrows(NotFoundException.class, () -> {
			bankService.getBank(64L);
		});
		Assertions.assertEquals(Constants.BANK_NOT_FOUND, exception.getMessage());
	}
}
