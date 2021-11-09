package com.testoper.paymentservice.util;

import java.time.Instant;

import com.testoper.paymentservice.entity.Payment;
import com.testoper.paymentservice.request.PaymentRequest;
import com.testoper.paymentservice.response.TransactionResponse;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
public class DataBuilder {

	public static Payment getMockPayment() {
		Payment payment = new Payment();
		payment.setAccountHolderName("Mario");
		payment.setAccountNumber("6758410035211161621602459");
		payment.setDate(Instant.now());
		payment.setId(729L);
		payment.setTransactionAmount(5000.00);
		payment.setBankName("Yes Bank");
		return payment;
	}

	public static PaymentRequest getMockPaymentRequest() {
		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAccountId(25L);
		paymentRequest.setBankId(16L);
		paymentRequest.setTransactionAmount(5000.00);
		return paymentRequest;
	}

	public static TransactionResponse getTransactionResponse() {
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setAccountHolderName("Mario");
		transactionResponse.setAccountNumber("6758410035211161621602459");
		transactionResponse.setBalance(55000.00);
		transactionResponse.setId(387L);
		transactionResponse.setBankName("Axis");
		return transactionResponse;
	}

}
