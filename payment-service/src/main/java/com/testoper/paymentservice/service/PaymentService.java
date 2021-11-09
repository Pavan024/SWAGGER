package com.testoper.paymentservice.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testoper.paymentservice.client.BankServiceClient;
import com.testoper.paymentservice.entity.Payment;
import com.testoper.paymentservice.mapper.PaymentMapper;
import com.testoper.paymentservice.repository.PaymentRepository;
import com.testoper.paymentservice.request.PaymentRequest;
import com.testoper.paymentservice.request.TransactionRequest;
import com.testoper.paymentservice.response.TransactionResponse;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@Service
public class PaymentService {

	@Autowired
	BankServiceClient bankServiceClient;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	AuthService authService;

	@Autowired
	PaymentMapper paymentMapper;

	private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

	/**
	 * 
	 * Makes Payment by calling Bank service
	 * 
	 * @param paymentRequest
	 * @return
	 */
	public Payment makePayment(PaymentRequest paymentRequest) {
		ModelMapper modelMapper = new ModelMapper();
		TransactionRequest transactionRequest = modelMapper.map(paymentRequest, TransactionRequest.class);
		log.info("Payment initiated");
		try {
		TransactionResponse transactionResponse = bankServiceClient.withdraw(authService.getAuthToken(),
				transactionRequest);
		log.info("Amount deducted from Bank Account");
		Payment payment = paymentMapper.mapTransactionResponseToPayment(transactionResponse,
				paymentRequest.getTransactionAmount());
		return paymentRepository.save(payment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
