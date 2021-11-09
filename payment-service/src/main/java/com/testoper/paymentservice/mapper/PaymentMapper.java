package com.testoper.paymentservice.mapper;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.testoper.paymentservice.entity.Payment;
import com.testoper.paymentservice.response.TransactionResponse;

@Service
public class PaymentMapper {

	/**
	 * 
	 * Maps Transaction Response to Payment Entity
	 * 
	 * @param transactionResponse
	 * @param transactionAmount
	 * @return
	 */
	public Payment mapTransactionResponseToPayment(TransactionResponse transactionResponse, Double transactionAmount) {
		return new Payment(transactionResponse.getAccountNumber(), transactionResponse.getAccountHolderName(),
				transactionAmount, transactionResponse.getBankName(), Instant.now());
	}

}
