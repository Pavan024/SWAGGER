package com.testoper.paymentservice.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testoper.paymentservice.client.BankServiceClient;
import com.testoper.paymentservice.mapper.PaymentMapper;
import com.testoper.paymentservice.repository.PaymentRepository;
import com.testoper.paymentservice.util.DataBuilder;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

	@InjectMocks
	PaymentService paymentService;

	@Mock
	PaymentRepository paymentRepository;

	@Mock
	BankServiceClient bankServiceClient;

	@Mock
	AuthService authService;
	
	@Mock
	PaymentMapper paymentMapper;

	@Test
	public void testMakePayment() {
		when(bankServiceClient.withdraw(Mockito.anyString(), Mockito.any()))
				.thenReturn(DataBuilder.getTransactionResponse());
		when(authService.getAuthToken()).thenReturn("Bearer eyJhbGciOiJIUzUxMiJ9.eyJ");
		when(paymentRepository.save(Mockito.any())).thenReturn(DataBuilder.getMockPayment());
		Assertions.assertEquals(DataBuilder.getMockPayment().getId(),
				paymentService.makePayment(DataBuilder.getMockPaymentRequest()).getId());
	}
}